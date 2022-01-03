package com.example.myapplication.data.network

import BalanceResponse
import com.example.myapplication.data.model.LoginResponse
import com.example.myapplication.data.model.RegisterResponse
import com.example.myapplication.data.model.RequestParameter
import com.example.myapplication.data.model.TransferRequest
import com.example.myapplication.utils.Constant.Companion.APPLICATION_JSON
import com.example.myapplication.utils.Constant.Companion.BALANCE
import com.example.myapplication.utils.Constant.Companion.CONTENT_TYPE
import com.example.myapplication.utils.Constant.Companion.LOGIN
import com.example.myapplication.utils.Constant.Companion.REGISTRATION
import com.example.myapplication.utils.Constant.Companion.TRANSACTIONS
import com.example.myapplication.utils.Constant.Companion.TRANSFER
import retrofit2.Response
import retrofit2.http.*


interface ApiEndPoints {

    @POST(LOGIN)
    @Headers(APPLICATION_JSON, CONTENT_TYPE)
    suspend fun userLogin(@Body requestModel: RequestParameter): Response<LoginResponse>

    @POST(REGISTRATION)
    @Headers(APPLICATION_JSON, CONTENT_TYPE)
    suspend fun userRegistration(@Body requestModel: RequestParameter): Response<RegisterResponse>

    @GET(BALANCE)
    @Headers(APPLICATION_JSON, CONTENT_TYPE)
    suspend fun userAccount(@Header("Authorization") token: String): Response<BalanceResponse>

    @GET(TRANSFER)
    @Headers(APPLICATION_JSON, CONTENT_TYPE)
    suspend fun userAmountTransfer(
        @Header("Authorization") token: String,
        @Body transferRequest: TransferRequest
    ): Response<Any>

}

