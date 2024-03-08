package com.example.viewmodel.simple

import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    var counter = 0

    fun counterPlus() {
        counter++
    }
    fun counterMinus() {
        counter--
    }
}