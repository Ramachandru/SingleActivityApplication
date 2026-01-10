package com.example.singleactivityapp.ui.screens.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S, I> : ViewModel() {
    private val _uiState = MutableStateFlow<S>(initState())
    val uiState: StateFlow<S> = _uiState

    abstract fun initState(): S

    protected fun updateUiState(state: S) {
        _uiState.value = state
    }

    abstract suspend fun loadData()

    abstract fun handleIntent(intent: I)
}