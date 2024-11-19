package com.example.electronicjournal.presenter.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicjournal.data.module.Student
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
): ViewModel() {

    private val _uiState = MutableLiveData<List<JournalItem>>()
    val uiState: LiveData<List<JournalItem>>
        get() = _uiState

    fun getTimetable(groupId: Int) {
        viewModelScope.launch {
            val result: MutableList<JournalItem> = mutableListOf()
            val res = getTimetableUseCase.invoke(groupId)
            for((day, lesson) in res){
                result.add(JournalDay(day))
                for(el in lesson){
                    result.add(JournalLesson(
                        el.lesson_start_time,
                        el.lesson_end_time,
                        el.lesson,
                        el.type_lesson,
                        el.classroom))
                }
            }
            _uiState.value = result
        }
    }
}