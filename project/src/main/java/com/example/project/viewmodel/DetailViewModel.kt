package com.example.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.db.FabEntity
import com.example.project.models.ResponseDetail
import com.example.project.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    val detail = MutableLiveData<ResponseDetail>()
    val loading = MutableLiveData<Boolean>()

    fun getDetail(id: Int) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.getDetail(id)

        if (response.isSuccessful) {
            detail.postValue(response.body())
        }

        loading.postValue(false)
    }

    //Database
    val isFavorite = MutableLiveData<Boolean>()
    suspend fun existMovie(id: Int) =
        withContext(viewModelScope.coroutineContext) { repository.existMovie(id) }

    fun favoriteMovie(id: Int, fabEntity: FabEntity) = viewModelScope.launch {
        val exist = repository.existMovie(id)
        if (exist) {
            isFavorite.postValue(false)
            repository.deleteMovie(fabEntity)
        } else {
            isFavorite.postValue(true)
            repository.insertMovie(fabEntity)
        }
    }
}