package com.eem.showdetails.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.eem.androidcommon.ui.base.BaseViewModel
import com.eem.showdetails.navigation.SHOW_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val showId: String = checkNotNull(savedStateHandle[SHOW_ID_ARG])

    var uiState by mutableStateOf(UIState())
        private set

    fun checkShowID() {
        emitUiEvent(BaseEvent.OnShowSnackBar(showId))
    }

    private fun handleErrorState(message: String?) {
        message?.let { emitUiEvent(BaseEvent.OnShowSnackBar(message)) }
    }

    private fun startLoading() {
        uiState = uiState.copy(isLoading = true)
    }

    private fun stopLoading() {
        uiState = uiState.copy(isLoading = false)
    }

    fun onUIEvent(uiEvent: UIEvent) {
    }

    sealed class UIEvent

    sealed class BaseEvent {
        data class OnShowSnackBar(val message: String) : BaseEvent()
    }

    data class UIState(
        val isLoading: Boolean = false
    )
}
