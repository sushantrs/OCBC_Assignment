package com.example.myapplication.utils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Class is used for the coroutine dispatcher
 */
object CoroutineContextProvider {
     private var TAG = CoroutineContextProvider::class.java.simpleName
     fun dispatcherMain(): CoroutineContext  = Dispatchers.Main+ handler
     fun dispatcherIO(): CoroutineContext = Dispatchers.IO+ handler
     val handler = CoroutineExceptionHandler { _, exception ->
          Log.v(TAG, "CoroutineExceptionHandler got $exception")
     }
}