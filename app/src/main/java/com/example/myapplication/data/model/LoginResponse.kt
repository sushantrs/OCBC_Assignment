package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") var status: String? = null,
    @SerializedName("token") var token: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("accountNo") var accountNo: String? = null
)
