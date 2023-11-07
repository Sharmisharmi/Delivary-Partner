package com.example.elitedelivery.profile

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityBankDetailsBinding


class BankDetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBankDetailsBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBankDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)





        binding.backpressImage.setOnClickListener {
            startActivity(Intent(this,MenuActivity::class.java))
        }

        binding.conitnueLl.setOnClickListener {
//            sendNotification()
        }
    }




    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MenuActivity::class.java))

    }
}