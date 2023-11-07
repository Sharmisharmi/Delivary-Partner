package com.example.elitedelivery.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elitedelivery.signup.adapter.OnBoardingAdapter
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.ActivitySkipScreenBinding

class SkipScreen : AppCompatActivity() {

    private lateinit var binding:ActivitySkipScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkipScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val images = arrayListOf(
            R.drawable.onboarding3,
            R.drawable.onboarding2, R.drawable.onboarding1
        )
        val text1 = arrayListOf("Start earning right after the joining","More you deliver, More you earn","Secure your future by staying longer")
        val text2 = arrayListOf("You can earn for every order you deliver and get paid at the end of the month.","Perform well and earn lot more incentives for your performance","Get lot of benefits reserved for our long term partners")
        binding.carouselRecyclerview.setHasFixedSize(true)
        binding.carouselRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.carouselRecyclerview.adapter = OnBoardingAdapter(this,images,text1,text2)
        binding.carouselRecyclerview.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        binding.conitnueLl.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}