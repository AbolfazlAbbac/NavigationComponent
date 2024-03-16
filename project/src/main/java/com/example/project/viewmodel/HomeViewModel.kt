package com.example.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.ResponseGenres
import com.example.project.models.ResponseMovieList
import com.example.project.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val topMoviesList = MutableLiveData<ResponseMovieList>()
    val allGenres = MutableLiveData<ResponseGenres>()

    fun topMovies(id: Int) = viewModelScope.launch {
        val responseTopList = repository.getTopMovies(id)
        if (responseTopList.isSuccessful) {
            topMoviesList.postValue(responseTopList.body())
        }
    }

    fun allGenres() = viewModelScope.launch {
        val response = repository.getAllGenres()
        if (response.isSuccessful) {
            allGenres.postValue(response.body())
        }
    }

}