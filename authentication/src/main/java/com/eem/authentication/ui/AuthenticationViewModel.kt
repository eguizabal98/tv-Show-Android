package com.eem.authentication.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.eem.androidcommon.R
import com.eem.androidcommon.ui.base.BaseViewModel
import com.eem.androidcommon.ui.base.ResourceProvider
import com.eem.domain.interactor.authentication.GetRequestTokenUseCase
import com.eem.domain.interactor.authentication.GetSessionIdUseCase
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.flow.collectLatest

class AuthenticationViewModel(
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val getSessionIdUseCase: GetSessionIdUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    var uiState by mutableStateOf(UIState())
        private set

    private fun getGuestToken() = executeUseCase {
        getRequestTokenUseCase().collectLatest {
            when (it) {
                ResultWrapper.Loading -> {
                    startLoading()
                }

                is ResultWrapper.Success -> {
                    stopLoading()
                    emitUiEvent(BaseEvent.OnShowCustomTab(it.data.requestToken))
                }

                is ResultWrapper.Error -> {
                    handleErrorState(it.message)
                    stopLoading()
                }

                ResultWrapper.NetworkError -> {
                    handleErrorState(resourceProvider.getString(R.string.network_error_message))
                    stopLoading()
                }
            }
        }
    }

    fun getSessionId(token: String) = executeUseCase {
        getSessionIdUseCase(SessionIdRequest(token)).collectLatest {
            when (it) {
                ResultWrapper.Loading -> {
                    startLoading()
                }

                is ResultWrapper.Success -> {
                    stopLoading()
                    Log.d("Session", it.data.sessionId)
                    emitUiEvent(BaseEvent.OnOpenHome)
                }

                is ResultWrapper.Error -> {
                    handleErrorState(it.message)
                    stopLoading()
                }

                ResultWrapper.NetworkError -> {
                    handleErrorState(resourceProvider.getString(R.string.network_error_message))
                    stopLoading()
                }
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
        when (uiEvent) {
            is UIEvent.OnInitAuthentication -> getGuestToken()
        }
    }

    sealed class UIEvent {
        object OnInitAuthentication : UIEvent()
    }

    sealed class BaseEvent {
        data class OnShowCustomTab(val token: String) : BaseEvent()
        data class OnShowSnackBar(val message: String) : BaseEvent()
        object OnOpenHome : BaseEvent()
    }

    data class UIState(
        val isLoading: Boolean = false
    )
}
