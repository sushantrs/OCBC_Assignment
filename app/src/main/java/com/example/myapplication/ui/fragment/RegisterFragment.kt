package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.model.RequestParameter
import com.example.myapplication.ui.viewmodel.RegisterVIewModel
import com.example.myapplication.utils.toast
import com.ocbc.ocbcassignment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*

/**
 *  register fragment used for registration
 */
class RegisterFragment : BaseFragment() {

    lateinit var navController: NavController

    override fun getViewModel(): RegisterVIewModel? {
        return activity?.let {
            ViewModelProvider(it).get(RegisterVIewModel::class.java)
        }
    }

    override fun init() {
        super.init()
        getViewModel()?.failedResponse?.observe(viewLifecycleOwner, {
            if (it) {
                context?.toast(getString(R.string.login_failed))
            } else {
                context?.toast(getString(R.string.regsiter_success))
                navController.navigate(R.id.action_registerFragment_to_loginfragment)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnRegister.setOnClickListener {
            val strUsername = edtUsername.text.toString()
            val strPassword = edtPassowrd.text.toString()
            val strConfirmPassword = edtConfirmPassowrd.text.toString()
            if (validateUserInput(strUsername, strPassword, strConfirmPassword)) {
                val registerRequest =
                    RequestParameter(edtUsername.text.toString(), edtPassowrd.text.toString())
                getViewModel()?.registerAPICall(registerRequest)
            }
        }

        txtback.setOnClickListener {
            navController.popBackStack()
        }

    }


    /**
     *  fun used for the validating the user input
     */
    private fun validateUserInput(
        strUsername: String,
        strPassword: String,
        strConfirmPassword: String
    ): Boolean {
        if (strUsername.isEmpty()) {
            edtUsername.requestFocus()
            edtUsername.setError(getString(R.string.error_msg))
            return false
        } else if (strPassword.isEmpty()) {
            edtPassowrd.requestFocus()
            edtPassowrd.setError(getString(R.string.error_msg_passwd))
            return false
        } else if (strConfirmPassword.isEmpty()) {
            edtConfirmPassowrd.requestFocus()
            edtConfirmPassowrd.setError(getString(R.string.error_msg_passwd))
            return false
        } else if (!strPassword.equals(strConfirmPassword)) {
            edtConfirmPassowrd.requestFocus()
            edtConfirmPassowrd.setError(getString(R.string.passwd_not_match))
            return false
        }
        return true
    }
}