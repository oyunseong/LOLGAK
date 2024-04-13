package com.oys.lolgak.extensions

import android.util.Log

fun String.log(tag: String = "fastLog") {
    Log.d(tag, this)
}