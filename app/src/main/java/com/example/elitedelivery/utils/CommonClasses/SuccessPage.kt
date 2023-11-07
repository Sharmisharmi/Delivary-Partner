package com.example.elitedelivery.utils.CommonClasses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivitySuccessPageBinding
import com.example.elitedelivery.signup.SkipScreen
import java.lang.Exception

class SuccessPage : AppCompatActivity() {

    private lateinit var binding:ActivitySuccessPageBinding

    var getIntent = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tickViewAccent.toggle()

        try {
            getIntent = intent.getStringExtra("IntentFrom")!!
            binding.done.text = "Completed"
            binding.successMsg.text = "You've successfully finish this delivery"
            binding.desc.visibility = View.GONE
        }catch (Ex:Exception){
            Ex.printStackTrace()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            when(getIntent){
                "MapActivity"->{
                    startActivity(intent.putExtra("IntentFrom","MapActivity"))
                    finish()
                }
                else->{
                    startActivity(intent.putExtra("IntentFrom","SuccessPage"))
                    finish()
                }
            }

        }, 3500)
    }
}