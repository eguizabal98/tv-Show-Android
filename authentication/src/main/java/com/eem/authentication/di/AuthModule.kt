package com.eem.authentication.di

import com.eem.authentication.ui.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authUiModule = module {
    viewModelOf(::AuthenticationViewModel)
}
