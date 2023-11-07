package com.example.elitedelivery.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backpressImage.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,ProfileActivity::class.java))
    }
}