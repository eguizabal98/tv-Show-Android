package com.eem.androidcommon.util

fun String.replaceNavArgument(argument: String, value: Any) =
    this.replace("{$argument}", value.toString())
