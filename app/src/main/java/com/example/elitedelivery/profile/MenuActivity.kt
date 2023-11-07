package com.example.elitedelivery.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elitedelivery.DataBinderMapperImpl
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.incentiveCard.setOnClickListener {
            startActivity(Intent(this,IncentivesDetails::class.java))
        }

        binding.profileCard.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

        binding.bankAccCard.setOnClickListener {
            startActivity(Intent(this,BankDetailsActivity::class.java))
        }

        binding.withdrawCard.setOnClickListener {
            startActivity(Intent(this,WithDrawActivity::class.java).putExtra("IntentFrom","MenuActivity"))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }
}