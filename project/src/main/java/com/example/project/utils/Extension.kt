package com.example.project.utils

import android.view.View

fun View.isShown(boolean: Boolean) {
    if (boolean) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun View.isClickableExtension(boolean: Boolean) {
    this.isClickable = boolean
}