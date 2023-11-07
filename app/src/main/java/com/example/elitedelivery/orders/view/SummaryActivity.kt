package com.example.elitedelivery.orders.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tickViewAccent.toggle()
        binding.walletDescTxt.setText(Html.fromHtml("Delivery Charge " + "<font color=red>" + "\u20b9 35.00 " + "</font>"+"added to your wallet"));

        binding.walletLL.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java).putExtra("IntentFrom","SuccessPage"))
        }


    }
}