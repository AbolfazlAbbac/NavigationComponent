package com.example.project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.BodyRegister
import com.example.project.models.ResponseRegister
import com.example.project.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    ViewModel() {

    val registerUser = MutableLiveData<ResponseRegister>()
    val loading = MutableLiveData<Boolean>()

    fun registerUser(bodyRegister: BodyRegister) = viewModelScope.launch {
        loading.postValue(true)

        val response = registerRepository.registerUser(bodyRegister)
        if (response.isSuccessful) {
            registerUser.postValue(response.body())
        }
        loading.postValue(false)
    }
}