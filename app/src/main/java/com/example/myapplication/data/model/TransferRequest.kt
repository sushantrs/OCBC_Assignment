package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class TransferRequest(
    @SerializedName("receipientAccountNo" ) var receipientAccountNo : String? = null,
    @SerializedName("amount"              ) var amount              : Int?    = null,
    @SerializedName("description"         ) var description         : String? = null
)
