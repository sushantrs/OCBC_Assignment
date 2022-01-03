package com.example.myapplication.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.TransferRequest
import com.example.myapplication.data.repository.RemoteRepository
import com.example.myapplication.utils.CoroutineContextProvider
import com.ocbc.ocbcassignment.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TransferViewModel(application: Application) : BaseViewModel(application) {

    var job: Job? = null
    val failedResponse= MutableLiveData<Boolean>()

    fun transferAccunt(tokenString: String,transferRequest: TransferRequest) {
        job = viewModelScope.launch(CoroutineContextProvider.dispatcherIO()) {
            RemoteRepository.getInstance()
                .userAmountTransfer(tokenString,transferRequest)?.let {
                    if (it.isSuccessful) {
                        failedResponse.postValue(false)
                    }else{
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