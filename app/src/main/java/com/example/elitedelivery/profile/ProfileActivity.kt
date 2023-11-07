package com.example.elitedelivery.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elitedelivery.R
import com.example.elitedelivery.dashboard.EarningsActivity
import com.example.elitedelivery.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding:ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backpressImage.setOnClickListener {
            startActivity(Intent(this,MenuActivity::class.java))
        }

        binding.editProfileTXT.setOnClickListener {
            startActivity(Intent(this,EditProfileActivity::class.java))
        }

        binding.earningsLL.setOnClickListener { startActivity(Intent(this,EarningsActivity::class.java).putExtra("IntentFrom","ProfileActivity")) }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MenuActivity::class.java))

    }
}