package com.example.myapplication.ui.fragment

import android.content.Context
import android.os.BaseBundle
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.model.RequestParameter
import com.example.myapplication.data.model.TransferRequest
import com.example.myapplication.ui.viewmodel.LoginViewModel
import com.example.myapplication.ui.viewmodel.TransferViewModel
import com.example.myapplication.utils.Constant
import com.example.myapplication.utils.toast
import com.ocbc.ocbcassignment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_transfer.*
import kotlinx.android.synthetic.main.progressbar_layout.*


class TransferFragment : BaseFragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer, container, false)
    }

    override fun init() {
        super.init()
        getViewModel()?.failedResponse?.observe(viewLifecycleOwner, {
            if (it) {
                progressBar.visibility=View.GONE
                context?.toast(getString(R.string.transefer_failed))
            }
            else{
                progressBar.visibility=View.GONE
                context?.toast(getString(R.string.transefer_failed))
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        txtback.setOnClickListener {
            navController.popBackStack()
        }



        btnTransferNow.setOnClickListener {
            val transferRequest = TransferRequest(
                "3631-757-7532",
                12,
                edtTransferDesc.text.toString()
            )
            getViewModel()?.transferAccunt(getTokenValue(), transferRequest)
            progressBar.visibility=View.VISIBLE
           }

    }


    private fun getTokenValue(): String {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val token = sharedPref?.getString(Constant.SH_KEY_TOKEN, "823gajsb dksad-9as")
        return token.toString()
    }

    override fun getViewModel(): TransferViewModel? {
        return activity?.let {
            ViewModelProvider(it).get(TransferViewModel::class.java)
        }
    }


}