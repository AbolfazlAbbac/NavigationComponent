package com.example.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.ResponseMovieList
import com.example.project.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    val searchedMovies = MutableLiveData<ResponseMovieList>()
    val loading = MutableLiveData<Boolean>()
    val emptyList = MutableLiveData<Boolean>()

    fun searchMovies(name: String) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.searchMovies(name)
        if (response.isSuccessful) {
            if (response.body()?.data!!.isNotEmpty()) {
                searchedMovies.postValue(response.body())
                emptyList.postValue(false)
            } else {
                emptyList.postValue(true)
            }
        }
        loading.postValue(false)
    }

}