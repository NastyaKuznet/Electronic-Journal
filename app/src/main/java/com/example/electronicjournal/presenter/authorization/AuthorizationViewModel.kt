package com.example.electronicjournal.presenter.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electronicjournal.data.module.Student
import com.example.electronicjournal.data.module.User
import com.example.electronicjournal.domain.AuthorizationUseCase
import com.example.electronicjournal.presenter.models.ResultUIState
import com.example.electronicjournal.presenter.models.ResultUIUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthorizationViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<ResultUIUser>(
        ResultUIUser(
            Student(
                0,
                "",
                "",
                "",
                "",
                "",
                false,
                0
            ), ResultUIState.Initial))
    val uiState: StateFlow<ResultUIUser> = _uiState.asStateFlow()

    fun registration(nickname: String, password: String){
        _uiState.value = ResultUIUser(
            Student(
                0,
                "",
                "",
                "",
                "",
                "",
                false,
                0
            ),
            ResultUIState.Loading)
        viewModelScope.launch {
            val result = authorizationUseCase.invoke(nickname, password)
            _uiState.value = ResultUIUser(result.user,
                when(result.stateResult){
                    true -> ResultUIState.Success
                    false -> ResultUIState.Error
                })
        }
    }
}