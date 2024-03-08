package com.example.viewmodel.recyclerview

import androidx.lifecycle.ViewModel
import com.example.viewmodel.Utils

class ViewModelRv : ViewModel() {

    val items = Utils.getItems()
}