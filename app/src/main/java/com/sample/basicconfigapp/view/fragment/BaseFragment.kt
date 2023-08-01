package com.sample.basicconfigapp.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sample.basicconfigapp.utils.callbacks.ClickHelper
import com.sample.basicconfigapp.viewmodel.BaseViewModel
import java.util.logging.Logger


abstract class BaseFragment : Fragment(), ClickHelper {

    val TAG = javaClass.simpleName
    private var viewModel: BaseViewModel? = null
    val REQUEST_EXTERNAL_STORAGE = 1
    val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    companion object {
        const val PERMISSION_ALL: Int = 1
    }

    protected fun navigate(navDirections: NavDirections) {
        try {
            if (navDirections != null) {
                findNavController().navigate(navDirections)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = activity?.let { BaseViewModel(it.application) }
        hideKeyboard()

    }

    fun showToast(msg: String?) {
        activity?.runOnUiThread(Runnable {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        })
    }




    open fun hideKeyboard() {
        val v = activity?.window?.currentFocus
        if (v != null) {
            val imm: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }


/*    fun doLogOut() {
        activity?.finishAffinity()
        val intent = Intent(activity, SplashScreenActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }*/

    override fun onClick(view: View) {
        hideKeyboard()
        when (view.id) {
            /*  R.id.iv_back->{
                  goBack()
              }*/
        }
    }

    override fun onClick(view: View, any: Any) {
        hideKeyboard()

    }

    open fun goBack() {
        findNavController().popBackStack()
    }

    fun showMessage(msg: String) {
        val snackbar = Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            msg,
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    fun showMessageOKCancel(
        message: String?,
        okListener: DialogInterface.OnClickListener?,
        isShowCancel: Boolean = true
    ) {
        AlertDialog.Builder(requireActivity())
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, okListener)
            .setNegativeButton(android.R.string.cancel, null)
            .create()
            .show()
    }


    internal fun showDialog(msg: String?) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage(msg)
            // if the dialog is cancelable
            .setCancelable(true)
            .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()

                goBack()
            })

        val alert = dialogBuilder.create()
        // alert.setTitle("Test")
        alert.show()
    }

    private fun checkPermission(): Boolean {
        if (isDeviceRequirePermissions) {
            val permissionNeeded: ArrayList<String> = ArrayList()
            val permissions = arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            //check for permissions
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(requireActivity(), permission) !=
                    PackageManager.PERMISSION_GRANTED
                ) {
                    permissionNeeded.add(permission)
                }
            }

            //Ask for not granted permission
            if (permissionNeeded.isNotEmpty()) {
                requestPermissions(
                    permissionNeeded.toArray(arrayOfNulls<String>(permissionNeeded.size)),
                    PERMISSION_ALL
                )
                return false
            }
        }
        return true
    }

    private val isDeviceRequirePermissions: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M



    private fun openSettings(): Boolean {
//        val intent = Intent(
//            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//            Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
//        )
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)
//        activity?.finish()
        return true
    }

    fun verifyStoragePermissions(activity: Activity?) {
        val permission = ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                requireActivity(),
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }



    private fun processPaymentData(status: Int, message: String?) {
        message?.let { Log.e("Payment", it) }
        onPayment(status, message)
    }

    open fun onPayment(status: Int, message: String?) {}

}