package com.example.myapplication.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.RequestParameter
import com.example.myapplication.data.model.LoginResponse
import com.example.myapplication.data.repository.RemoteRepository
import com.example.myapplication.utils.CoroutineContextProvider
import com.ocbc.ocbcassignment.base.BaseViewModel
import kotlinx.coroutines.*

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var job: Job? = null
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val failedResponse = MutableLiveData<Boolean>()

    fun loginAPICallInvoke(logInRequest: RequestParameter) {
        job = viewModelScope.launch(CoroutineContextProvider.dispatcherIO()) {
            RemoteRepository.getInstance()
                .userLogin(logInRequest)?.let {
                    if (it.isSuccessful) {
                        loginResponseLiveData.postValue(it.body())
                    } else {
                        failedResponse.postValue(true)
                    }
                }
        }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}