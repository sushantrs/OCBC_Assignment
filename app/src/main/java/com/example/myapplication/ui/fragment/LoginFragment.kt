package com.example.myapplication.ui.fragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.model.LoginResponse
import com.example.myapplication.data.model.RequestParameter
import com.example.myapplication.ui.viewmodel.LoginViewModel
import com.example.myapplication.utils.Constant
import com.example.myapplication.utils.Constant.Companion.SH_KEY_ACCOUNT_HOLDER
import com.example.myapplication.utils.Constant.Companion.SH_KEY_ACCOUNT_NO
import com.example.myapplication.utils.Constant.Companion.SH_KEY_TOKEN
import com.example.myapplication.utils.toast
import com.ocbc.ocbcassignment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.progressbar_layout.*

/**
 *  fragment is used for the login screen
 */
class LoginFragment : BaseFragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnLogin.setOnClickListener {
            validateUserInput()
        }
        btnRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    /**
     *  fun used for the validating the user input
     */
    private fun validateUserInput() {
        when {
            TextUtils.isEmpty(edtLoignUserName.text.toString()) -> {
                edtLoignUserName.requestFocus()
                edtLoignUserName.setError(getString(R.string.error_msg))
            }
            TextUtils.isEmpty(edtLoginPassword.text.toString()) -> {
                edtLoginPassword.requestFocus()
                edtLoginPassword.setError(getString(R.string.error_msg_passwd))
            }
            else -> {
                val loginRequest =
                    RequestParameter(
                        edtLoignUserName.text.toString(),
                        edtLoginPassword.text.toString()
                    )
                getViewModel()?.loginAPICallInvoke(loginRequest)
                progressBar.visibility=View.VISIBLE
            }
        }
    }

    override fun init() {
        super.init()
        getViewModel()?.loginResponseLiveData?.observe(viewLifecycleOwner, {
            if (it.status.equals("success")) {
                saveData(it)
                context?.toast(getString(R.string.login_success))
                val bundle = Bundle()
                bundle.putString(SH_KEY_TOKEN, it.token)
                bundle.putString(SH_KEY_ACCOUNT_NO, it.accountNo)
                bundle.putString(SH_KEY_ACCOUNT_HOLDER, it.username)
                progressBar.visibility=View.GONE
                navController.navigate(R.id.action_loginFragment_to_DashboardFragment, bundle)
            }
        })

        getViewModel()?.failedResponse?.observe(viewLifecycleOwner, {
            if (it) {
                progressBar.visibility=View.GONE
                context?.toast(getString(R.string.login_failed))
            }
        })

    }

    /**
     *  fun used for storing the data
     */
    private fun saveData(it: LoginResponse?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(Constant.SH_KEY_ACCOUNT_NO, it?.accountNo)
            putString(Constant.SH_KEY_TOKEN, it?.token)
            putString(Constant.SH_KEY_ACCOUNT_HOLDER, it?.username)
            apply()
        }
    }

    override fun getViewModel(): LoginViewModel? {
        return activity?.let {
            ViewModelProvider(it).get(LoginViewModel::class.java)
        }
    }

}

