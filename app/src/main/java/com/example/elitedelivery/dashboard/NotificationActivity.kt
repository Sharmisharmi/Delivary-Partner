package com.example.elitedelivery.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityNotificationBinding
import com.example.elitedelivery.databinding.ActivityNotificationViewBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backpressImage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}