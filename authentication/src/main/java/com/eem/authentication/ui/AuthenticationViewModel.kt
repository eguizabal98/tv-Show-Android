package com.eem.authentication.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.eem.androidcommon.ui.base.BaseViewModel
import com.eem.domain.interactor.authentication.GetRequestTokenUseCase
import com.eem.domain.interactor.authentication.GetSessionIdUseCase
import com.eem.domain.interactor.authentication.IsLoggedUseCase
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.Failure
import com.eem.domain.model.result.Loading
import com.eem.domain.model.result.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val getRequestTokenUseCase: GetRequestTokenUseCase,
    private val getSessionIdUseCase: GetSessionIdUseCase,
    private val isLoggedUseCase: IsLoggedUseCase
) : BaseViewModel() {

    var uiState by mutableStateOf(UIState())
        private set

    private fun getGuestToken() = executeUseCase {
        when (val result = getRequestTokenUseCase()) {
            Loading -> {
                startLoading()
            }

            is Success -> {
                stopLoading()
                emitUiEvent(BaseEvent.OnShowCustomTab(result.data.requestToken))
            }

            is Failure -> {
                handleErrorState(result.httpError.throwable.message)
                stopLoading()
            }
        }
    }

    fun getSessionId(token: String) = executeUseCase {
        when (val result = getSessionIdUseCase(SessionIdRequest(token))) {
            Loading -> {
                startLoading()
            }

            is Success -> {
                stopLoading()
                emitUiEvent(BaseEvent.OnOpenHome)
            }

            is Failure -> {
                handleErrorState(result.httpError.throwable.message)
                stopLoading()
            }
        }
    }

    fun getIsLogged() = executeUseCase {
        when (val result = isLoggedUseCase()) {
            Loading -> {
                startLoading()
            }
            is Success -> {
                stopLoading()
                if (result.data) {
                    emitUiEvent(BaseEvent.OnOpenHome)
                } else {
                    uiState = uiState.copy(showAuthentication = true)
                }
            }
            is Failure -> {
                handleErrorState(result.httpError.throwable.message)
                stopLoading()
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
        val isLoading: Boolean = false,
        val showAuthentication: Boolean = false
    )
}
