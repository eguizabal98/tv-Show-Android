package com.eem.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.eem.androidcommon.ui.base.BaseViewModel
import com.eem.domain.interactor.tvshow.GetLastFilterUseCase
import com.eem.domain.interactor.tvshow.GetTvShowUseCase
import com.eem.domain.interactor.tvshow.SetFilterUseCase
import com.eem.domain.model.result.Failure
import com.eem.domain.model.result.Loading
import com.eem.domain.model.result.Success
import com.eem.domain.model.tvshow.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTvShowUseCase: GetTvShowUseCase,
    private val getLastFilterUseCase: GetLastFilterUseCase,
    private val setFilterUseCase: SetFilterUseCase
) : BaseViewModel() {

    var uiState by mutableStateOf(UIState())
        private set

    fun getTvShows() = getTvShowUseCase().cachedIn(viewModelScope)

    private fun handleErrorState(message: String?) {
        message?.let { emitUiEvent(BaseEvent.OnShowSnackBar(message)) }
    }

    fun getLastFilter() = executeUseCase {
        when (val result = getLastFilterUseCase()) {
            Loading -> {
                startLoading()
            }

            is Success -> {
                stopLoading()
                Filter.fromString(result.data)?.let {
                    uiState = uiState.copy(filterSelected = it.filter)
                }
            }

            is Failure -> {
                stopLoading()
                handleErrorState(result.httpError.throwable.message)
            }
        }
    }

    fun setFilter(filter: String) = executeUseCase {
        when (val result = setFilterUseCase(filter)) {
            Loading -> {
                startLoading()
            }

            is Success -> {
                stopLoading()
                if (result.data) {
                    emitUiEvent(BaseEvent.OnFilterChange)
                    getLastFilter()
                }
            }

            is Failure -> {
                stopLoading()
                handleErrorState(result.httpError.throwable.message)
            }
        }
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
        object OnFilterChange : BaseEvent()
    }

    data class UIState(
        val isLoading: Boolean = false,
        val filterSelected: String = ""
    )
}
