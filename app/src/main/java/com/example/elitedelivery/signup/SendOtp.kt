package com.example.elitedelivery.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.databinding.ActivitySendOtpBinding
import com.example.elitedelivery.utils.CommonMethods

class SendOtp : AppCompatActivity() {
    private lateinit var binding: ActivitySendOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.verifyLL.setOnClickListener {
            CommonMethods.showAlert(this@SendOtp, MainActivity::class.java, "DashBoard")
        }

    }

}