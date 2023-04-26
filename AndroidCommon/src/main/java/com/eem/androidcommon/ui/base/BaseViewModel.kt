package com.eem.androidcommon.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    var baseEvent = MutableSharedFlow<Any>()

    inline fun executeUseCase(
        crossinline action: suspend () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            action()
        }
    }

    fun emitUiEvent(data: Any) {
        viewModelScope.launch {
            baseEvent.emit(data)
        }
    }
}
