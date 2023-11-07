package com.example.elitedelivery.orders.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.databinding.ActivityOrderDetailBinding
import com.example.elitedelivery.utils.CommonMethods

class OrderDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityOrderDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backpressImage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java).putExtra("IntentFrom","OrderDetailActivity"))
        }
        binding.acceptTXT.setOnClickListener {
            CommonMethods.showAlert(this,MainActivity::class.java,"MapActivity")


        }


    }
}