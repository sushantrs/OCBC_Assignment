package com.ocbc.ocbcassignment.base

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    open fun getViewModel(): BaseViewModel? {
        return null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //networkObserverUtility = NetworkObserverUtility(context, lifecycle, this)
        init()
    }

    open fun init() {
        val baseViewModel = getViewModel()
    }
}