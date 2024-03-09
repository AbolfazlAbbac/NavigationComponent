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
    private val progressBar = MutableLiveData<Boolean>()

    fun registerUser(bodyRegister: BodyRegister) = viewModelScope.launch {
        progressBar.postValue(true)

        val response = registerRepository.registerUser(bodyRegister)
        if (response.isSuccessful) {
            registerUser.postValue(response.body())
        }
        progressBar.postValue(false)
    }
}