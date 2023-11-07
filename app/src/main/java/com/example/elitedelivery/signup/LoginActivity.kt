package com.example.elitedelivery.signup

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding


    var phoneNumber = ""
    var clickevent = 0

    var doubleTap :Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

      GetNumber()

        binding.sentOtp.setOnClickListener {
            startActivity(Intent(this,SendOtp::class.java))
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        binding.phoneEt.setOnClickListener {
            if (clickevent == 0) {
                if (!phoneNumber.isNullOrEmpty()) {
                    clickevent = 1
                    showAlert(phoneNumber)
                    binding.phoneEt.isFocusable = true
                    binding.phoneEt.isEnabled = true
                    binding.phoneEt.isClickable = true
                   hidekeyBoard(binding.phoneEt)
                }
            }else if (clickevent == 1) {
                binding.phoneEt.isFocusable = true
                binding.phoneEt.isEnabled = true
                binding.phoneEt.isClickable = true
                showkeyBoard(this,binding.phoneEt)
            }
        }

    }

    private fun hidekeyBoard(view: View) {
        view?.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showkeyBoard(activity: LoginActivity, view: View) {
        try {
            val input = activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            input.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    fun GetNumber() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_SMS
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_NUMBERS
            ) ==
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission check

            // Create obj of TelephonyManager and ask for current telephone service
            val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
             phoneNumber = telephonyManager.line1Number
//            binding.phoneEt!!.setText(phoneNumber)
            return
        } else {
            // Ask for permission
            requestPermission()
        }
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    Manifest.permission.READ_PHONE_STATE
                ), 100
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                val telephonyManager = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_PHONE_NUMBERS
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_PHONE_STATE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                 phoneNumber = telephonyManager.line1Number

//                binding.phoneEt!!.setText(phoneNumber)
            }
            else -> throw IllegalStateException("Unexpected value: $requestCode")
        }
    }

    private fun showAlert(phoneNumber: String) {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.phone_number,null)
        builder.setView(view)
        builder.setCanceledOnTouchOutside(false)

        var phoneText: TextView = view.findViewById(R.id.phone_txt)

        phoneText.text = phoneNumber
        phoneText.setOnClickListener {
            binding.phoneEt.setText(phoneNumber)
            builder.dismiss()
        }


        builder.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (doubleTap) {
            val builder = android.app.AlertDialog.Builder(this,R.style.CustomAlertDialog)
                .create()
            val view = LayoutInflater.from(this).inflate(R.layout.logout_check_dialog,null)
            val  delete_ll = view.findViewById<LinearLayout>(R.id.delete_ll)
            val  cancel_ll = view.findViewById<LinearLayout>(R.id.cancel_ll)
            builder.setView(view)
            cancel_ll.setOnClickListener {
                builder.dismiss()
            }
            delete_ll.setOnClickListener {

                finishAffinity()
            }
            builder.setCanceledOnTouchOutside(false)
            builder.show()

        }
        else {
            Toast.makeText(
                applicationContext,
                "Press twice to exit from the app",
                Toast.LENGTH_SHORT
            ).show()
        }
        doubleTap = true
        Handler().postDelayed({ doubleTap = false }, 2000)
    }

}