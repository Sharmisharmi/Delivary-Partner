package com.example.elitedelivery.dashboard

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elitedelivery.MainActivity
import com.example.elitedelivery.R
import com.example.elitedelivery.dashboard.adapter.DashBoardAdapter
import com.example.elitedelivery.databinding.FragmentDashBoardBinding
import com.example.elitedelivery.e_sampletracking.DummyActivity
import com.example.elitedelivery.profile.MenuActivity
import com.example.elitedelivery.profile.ProfileActivity
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class DashBoardFragment : Fragment() {

    private lateinit var binding: FragmentDashBoardBinding



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentDashBoardBinding.inflate(inflater, container, false)


        binding.marqueeTxt.isSelected = true
        binding.profileImage.setOnClickListener { startActivity(Intent(activity,ProfileActivity::class.java)) }
        binding.menuImage.setOnClickListener { startActivity(Intent(activity,MenuActivity::class.java)) }
        binding.completedDeliveryLL.setOnClickListener { startActivity(Intent(activity,DummyActivity::class.java)) }
        binding.earningsLL.setOnClickListener { startActivity(Intent(activity,EarningsActivity::class.java).putExtra("IntentFrom","DashBoard")) }
        binding.pendingDeliveryLL.setOnClickListener { startActivity(Intent(activity,MainActivity::class.java).putExtra("IntentFrom","MapActivity")) }
        animation()
        setAutoScrollRecyclerView()



        binding.notification.setOnClickListener {
            startActivity(Intent(activity,NotificationActivity::class.java))
        }
        return binding.root
    }

    private fun setAutoScrollRecyclerView() {

        var signInData = arrayListOf("Must complete 10 delivery for 1 day","Don't worry about fuel","Learn more about incentives")


        val images = arrayListOf(
            R.drawable.banner1,
            R.drawable.banner2, R.drawable.banner3
        )
        binding.carouselRecyclerview.setHasFixedSize(true)
        binding.carouselRecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.carouselRecyclerview.adapter = DashBoardAdapter(activity!!,images,signInData)
        binding.carouselRecyclerview.apply {
            setAlpha(true)
            setInfinite(true)
        }


    }


    private fun animation() {
        binding.comImg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate))
        binding.earningImg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate))
        binding.pendingImg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fade_in))
        binding.cancelImg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.shake))
        var shake = AnimationUtils.loadAnimation(activity,R.anim.shake)
        shake.repeatCount=1


        var rotate = AnimationUtils.loadAnimation(activity,R.anim.rotate)
        rotate.repeatCount=1
        binding.trophy.startAnimation(rotate)
        binding.congrats.startAnimation(rotate)


        binding.viewKonfetti.build()
            .addColors(Color.parseColor("#FFD700"), Color.parseColor("#FD7F20"),Color.parseColor("#4490FE"),Color.parseColor("#4CAF50"))
            .setDirection(0.0, 379.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.Square, Shape.Circle)
            .addSizes(Size(6))
            .setPosition(-250f, binding.viewKonfetti.width + 50f, -350f, -50f)
            .streamFor(500,1000)

        binding.typeWriterView.animateText("You have completed today task..!");
        binding.typeWriterView.setCharacterDelay(115);


    }


}