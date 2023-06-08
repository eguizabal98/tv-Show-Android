package com.eem.androidcommon.ui.base

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(private val context: Context) : ResourceProvider {

    override fun getString(@StringRes stringResId: Int): String = context.getString(stringResId)
}
