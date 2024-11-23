package com.example.electronicjournal.presenter.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicjournal.data.module.Student
import com.example.electronicjournal.domain.CurrentDatesWeekUseCase
import com.example.electronicjournal.domain.GetCurrentTimetableUseCase
import com.example.electronicjournal.presenter.journal.journalElements.JournalDay
import com.example.electronicjournal.presenter.journal.journalElements.JournalItem
import com.example.electronicjournal.presenter.journal.journalElements.JournalLesson
import com.example.electronicjournal.presenter.models.ResultUIState
import com.example.electronicjournal.presenter.models.ResultUIUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

class JournalViewModel @Inject constructor(
    private val getTimetableUseCase: GetCurrentTimetableUseCase,
    private val currentDatesWeekUseCase: CurrentDatesWeekUseCase
): ViewModel() {

    private val _uiState = MutableLiveData<List<JournalItem>>()
    val uiState: LiveData<List<JournalItem>>
        get() = _uiState

    fun getTimetable(groupId: Int) {
        viewModelScope.launch {
            val dates = currentDatesWeekUseCase.invoke()
            val result: MutableList<JournalItem> = mutableListOf()
            val res = getTimetableUseCase.invoke(groupId)
            for((day, lesson) in res){
                result.add(JournalDay(day,chooseDay(day, dates)))
                for(el in lesson){
                    result.add(JournalLesson(
                        el.id,
                        el.lesson_start_time.substring(0, 5),
                        el.lesson_end_time.substring(0, 5),
                        el.lesson,
                        el.type_lesson,
                        el.classroom,
                        el.name + " " + el.lastname + " " + el.patronymic,
                        chooseDay(day, dates)))
                }
            }
            _uiState.value = result
        }
    }

    private fun chooseDay(day: String, dates: Map<Int,Pair<Int, Int>>) =when(day){
        "Понедельник" -> dates[2]?.first.toString() + "." + dates[2]?.second.toString()
        "Вторник" -> dates[3]?.first.toString() + "." + dates[3]?.second.toString()
        "Среда" -> dates[4]?.first.toString() + "." + dates[4]?.second.toString()
        "Четверг" -> dates[5]?.first.toString() + "." + dates[5]?.second.toString()
        "Пятница" -> dates[6]?.first.toString() + "." + dates[6]?.second.toString()
        "Суббота" -> dates[7]?.first.toString() + "." + dates[7]?.second.toString()
        else -> ""
    }
}