package com.example.myapplication.ui.fragment

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.viewmodel.HomeViewModel
import com.example.myapplication.utils.Constant
import com.example.myapplication.utils.toast
import com.ocbc.ocbcassignment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*


class HomeFragment : BaseFragment() {

    lateinit var navController: NavController
    var token = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        btnTransfer.setOnClickListener {
            navController.navigate(R.id.action_dashboardFragment_to_transferFragment)
        }

        txtLogout.setOnClickListener {
            openAlertDiialog()
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(activity) {
            openAlertDiialog()
        }
        getViewModel()?.balanceResponseData?.observe(viewLifecycleOwner, {
            txtBalanceValue.text = it.balance.toString()
        })
    }

    private fun openAlertDiialog() {
        var alertDialogBuilder = AlertDialog.Builder(context, R.style.AlertDialogThemeCustom)
        alertDialogBuilder.setTitle(getString(R.string.logouttitle))
        alertDialogBuilder.setMessage(getString(R.string.dialogMessage))
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton(
            "ok",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
                context?.toast(getString(R.string.logout))
                val intent = Intent(getActivity(), MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                getActivity()?.startActivity(intent)
            })
        alertDialogBuilder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun init() {
        super.init()
        arguments?.let {
            token = it.getString(Constant.SH_KEY_TOKEN).toString()
            txtAccNoValue.text = it.getString(Constant.SH_KEY_ACCOUNT_NO).toString()
            txtAccountHolderValue.text = it.getString(Constant.SH_KEY_ACCOUNT_HOLDER).toString()
        }

        getViewModel()?.getAccountDetails(token)
    }

    override fun getViewModel(): HomeViewModel? {
        return activity?.let {
            ViewModelProvider(it).get(HomeViewModel::class.java)
        }
    }
}