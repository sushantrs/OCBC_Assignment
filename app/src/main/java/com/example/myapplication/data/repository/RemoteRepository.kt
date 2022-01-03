package com.example.myapplication.data.repository

import BalanceResponse
import com.example.myapplication.data.model.RequestParameter
import com.example.myapplication.data.model.LoginResponse
import com.example.myapplication.data.model.RegisterResponse
import com.example.myapplication.data.model.TransferRequest
import com.example.myapplication.data.network.RetroFitClient
import retrofit2.Response

class RemoteRepository {

    companion object {
        private val instance = RemoteRepository()

        fun getInstance(): RemoteRepository {
            return instance
        }
    }

    private var retrofitService = RetroFitClient.creteRetroFitInstance()


    suspend fun userLogin(loginRequestModel: RequestParameter): Response<LoginResponse>? {
        return retrofitService.userLogin(loginRequestModel)
    }

    suspend fun userRegistration(registrationRequestModel: RequestParameter): Response<RegisterResponse>? {
        return retrofitService.userRegistration(registrationRequestModel)
    }

    suspend fun userAccount(token:String): Response<BalanceResponse>? {
        return retrofitService.userAccount(token)
    }

    suspend fun userAmountTransfer(token:String,transferRequest: TransferRequest): Response<Any>? {
        return retrofitService.userAmountTransfer(token,transferRequest)
    }

}