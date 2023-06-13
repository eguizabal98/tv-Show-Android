package com.eem.showdetails.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.eem.androidcommon.ui.base.BaseViewModel
import com.eem.domain.interactor.tvshow.GetTvShowDetailsUseCase
import com.eem.domain.model.result.Failure
import com.eem.domain.model.result.Loading
import com.eem.domain.model.result.Success
import com.eem.domain.model.tvshow.TvShowDetails
import com.eem.showdetails.navigation.SHOW_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTvShowDetailsUseCase: GetTvShowDetailsUseCase
) : BaseViewModel() {

    private val showId: String = checkNotNull(savedStateHandle[SHOW_ID_ARG])

    var uiState by mutableStateOf(UIState())
        private set

    fun checkShowID() {
        if (showId.isNotEmpty()) getTvShowDetails(showId)
    }

    private fun getTvShowDetails(tvShowId: String) = executeUseCase {
        when (val result = getTvShowDetailsUseCase(tvShowId)) {
            Loading -> {
                startLoading()
            }
            is Success -> {
                stopLoading()
                uiState = uiState.copy(tvShowDetails = result.data)
            }
            is Failure -> {
                stopLoading()
                handleErrorState(result.httpError.throwable.message)
            }
        }
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
        val isLoading: Boolean = false,
        val tvShowDetails: TvShowDetails? = null
    )
}
