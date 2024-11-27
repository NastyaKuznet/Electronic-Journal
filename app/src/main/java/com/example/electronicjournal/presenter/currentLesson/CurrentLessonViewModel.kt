package com.example.electronicjournal.presenter.currentLesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicjournal.data.module.Attendance
import com.example.electronicjournal.data.module.AttendanceStudent
import com.example.electronicjournal.domain.CreateAttendanceUseCase
import com.example.electronicjournal.domain.DoneAttendanceUseCase
import com.example.electronicjournal.domain.GetStudentsUseCase
import com.example.electronicjournal.domain.OpenAttendanceUseCase
import com.example.electronicjournal.presenter.models.StudentAttention
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import javax.inject.Inject

class CurrentLessonViewModel@Inject constructor(
    private val getStudentsUseCase: GetStudentsUseCase,
    private val createAttendanceUseCase: CreateAttendanceUseCase,
    private val doneAttendanceUseCase: DoneAttendanceUseCase,
    private val openAttendanceUseCase: OpenAttendanceUseCase,
): ViewModel()  {

    private val _attendanceState = MutableLiveData<List<StudentAttention>>()
    val attendanceState: LiveData<List<StudentAttention>>
        get() = _attendanceState

    private val _saveState = MutableLiveData<Boolean>()
    val saveState: LiveData<Boolean>
        get() = _saveState

    private val _attendanceHas = MutableLiveData<Boolean>()
    val attendanceHas : LiveData<Boolean>
        get() = _attendanceHas

    private val _attendanceOld = MutableLiveData<List<AttendanceStudent>>()
    val attendanceOld : LiveData<List<AttendanceStudent>>
        get() = _attendanceOld

    private var attendance: Attendance = Attendance(0)

    fun getStudents(groupId: Int){
        viewModelScope.launch {
            val result = getStudentsUseCase.invoke(groupId)
            val r = mutableListOf<StudentAttention>()
            for(el in result){
                r.add(StudentAttention(el))
            }
            _attendanceState.value = r
        }
    }

    fun startAttendance(classId: Int, date: String){
        viewModelScope.launch {
            _attendanceOld.value = openAttendanceUseCase.invoke(classId, formatDate(date))
            if( _attendanceOld.value?.size!! == 0){
                attendance = createAttendanceUseCase.invoke(classId, formatDate(date))
                _attendanceHas.value = false
            } else{
                _attendanceHas.value = true
            }
        }
    }

    fun save(){
        viewModelScope.launch {
            if (_attendanceState.value != null && _attendanceState.value!!.isNotEmpty()) {
                _saveState.value = doneAttendanceUseCase.invoke(attendance.id, _attendanceState.value!!)
            }
        }
    }

    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = inputFormat.parse(dateString)
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR))
        return outputFormat.format(calendar.time)
    }

}