package com.example.electronicjournal.presenter.models

sealed class ResultUIState {
    data object Initial : ResultUIState()
    data object Loading : ResultUIState()
    data object Success : ResultUIState()
    data object Error : ResultUIState()
}