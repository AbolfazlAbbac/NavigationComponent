package com.example.livedata.simple

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class ViewModel : ViewModel() {

    val itemText: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    suspend fun changeText() {
        var i = 0
        while (i <= 2000) {
                itemText.postValue("Abolfazl $i")
            i++
        }
    }
}