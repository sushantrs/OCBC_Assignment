package com.example.myapplication.ui.viewmodel

import BalanceResponse
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.RemoteRepository
import com.example.myapplication.utils.CoroutineContextProvider
import com.ocbc.ocbcassignment.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    var balanceResponseData = MutableLiveData<BalanceResponse>()
    var job: Job? = null



    fun getAccountDetails(token:String) {


       job=viewModelScope.launch(CoroutineContextProvider.dispatcherIO()) {
            val response = RemoteRepository.getInstance()
                .userAccount(token)
            response?.let {
                if (it.isSuccessful) {
                      balanceResponseData.postValue(it.body())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}