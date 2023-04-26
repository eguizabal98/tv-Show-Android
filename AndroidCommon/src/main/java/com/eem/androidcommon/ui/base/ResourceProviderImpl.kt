package com.eem.androidcommon.ui.base

import android.content.Context
import androidx.annotation.StringRes

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes stringResId: Int): String = context.getString(stringResId)
}
