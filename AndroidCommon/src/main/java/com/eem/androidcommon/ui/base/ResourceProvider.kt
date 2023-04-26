package com.eem.androidcommon.ui.base

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringResId: Int): String
}
