package com.example.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.db.FabEntity
import com.example.project.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) :
    ViewModel() {

    val movieFabList = MutableLiveData<MutableList<FabEntity>>()
    val empty = MutableLiveData<Boolean>()

    fun getAllFabMovie() = viewModelScope.launch {
        val list = repository.getAllFavorites()
        if (list.isNotEmpty()) {
            movieFabList.postValue(list)
            empty.postValue(false)
        } else {
            empty.postValue(true)
        }
    }


}