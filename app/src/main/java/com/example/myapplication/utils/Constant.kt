package com.example.myapplication.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast

/**
 * Extension fun for toast msg
 */
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()



class Constant {

    companion object {
        const val APPLICATION_JSON = "Accept: application/json"
        const val CONTENT_TYPE = "Content-Type: application/json"
        const val LOGIN = "/login"
        const val REGISTRATION = "/register"
        const val BALANCE = "/balance"
        const val SH_KEY_ACCOUNT_NO = "account_no"
        const val SH_KEY_ACCOUNT_HOLDER = "account_holder"
        const val SH_KEY_TOKEN = "account_holder"
        const val SH_KEY_LOGIN = "login"
        const val PAYEES = "/payees"
        const val TRANSACTIONS = "/transactions"
        const val TRANSFER = "/transfer"




    }

}