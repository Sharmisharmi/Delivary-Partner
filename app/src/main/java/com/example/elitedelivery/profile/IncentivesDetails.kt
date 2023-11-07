package com.example.elitedelivery.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityIncentivesDetailsBinding

class IncentivesDetails : AppCompatActivity() {

    private lateinit var binding:ActivityIncentivesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncentivesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backpressImage.setOnClickListener {
            startActivity(Intent(this,MenuActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MenuActivity::class.java))

    }
}