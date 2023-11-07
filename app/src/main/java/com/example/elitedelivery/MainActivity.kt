package com.example.elitedelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.elitedelivery.dashboard.DashBoardFragment
import com.example.elitedelivery.my_orders.MyOrdersFragment
import com.example.elitedelivery.orders.OrdersFragment
import com.example.elitedelivery.wallet.WalletFragment
import nl.joery.animatedbottombar.AnimatedBottomBar
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    lateinit var bottom_bar: AnimatedBottomBar
    var doubleTap :Boolean = false

    var getIntent =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        try {
            getIntent = intent.getStringExtra("IntentFrom")!!
        }catch (EX:Exception){
            EX.printStackTrace()
        }

        fragmentSetting()

    }

    private fun fragmentSetting() {
        val dashboard_Fragment = DashBoardFragment()
        val orders_Fragment = OrdersFragment()
        val myorders_Fragment = MyOrdersFragment()
        val wallet_Fragment = WalletFragment()

        setCurrentFragment(dashboard_Fragment)
        when(getIntent){
            "OrderDetailActivity"-> {
                setCurrentFragment(orders_Fragment)
                bottom_bar.selectTabAt(1, true)
            }
            "MapActivity"-> {
                setCurrentFragment(myorders_Fragment)
                bottom_bar.selectTabAt(2, true)
            }
            "SuccessPage"-> {
                setCurrentFragment(wallet_Fragment)
                bottom_bar.selectTabAt(3, true)
            }
            "DashBoard" ->{
                setCurrentFragment(dashboard_Fragment)
                bottom_bar.selectTabAt(0, true)
            }
        }

        bottom_bar.onTabSelected = {
            when(it.id){
                (R.id.dashboard_tab)->setCurrentFragment(dashboard_Fragment)
                (R.id.orders_tab)->setCurrentFragment(orders_Fragment)
                (R.id.myorders_tab)->setCurrentFragment(myorders_Fragment)
                (R.id.wallet_tab)->setCurrentFragment(wallet_Fragment)
            }
        }


    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }

    private fun initView() {
        bottom_bar = findViewById(R.id.bottom_bar)
    }

    override fun onBackPressed() {
        if (doubleTap) {
            val builder = android.app.AlertDialog.Builder(this, R.style.CustomAlertDialog)
                .create()
            val view = LayoutInflater.from(this).inflate(R.layout.logout_check_dialog,null)
            val  delete_ll = view.findViewById<LinearLayout>(R.id.delete_ll)
            val  cancel_ll = view.findViewById<LinearLayout>(R.id.cancel_ll)
            builder.setView(view)
            cancel_ll.setOnClickListener {
                builder.dismiss()
            }
            delete_ll.setOnClickListener {
                finishAffinity()
            }
            builder.setCanceledOnTouchOutside(false)
            builder.show()

        }
        else {
            Toast.makeText(
                applicationContext,
                "Press twice to exit",
                Toast.LENGTH_SHORT
            ).show()
        }
        doubleTap = true
        Handler().postDelayed({ doubleTap = false }, 2000)
    }
}