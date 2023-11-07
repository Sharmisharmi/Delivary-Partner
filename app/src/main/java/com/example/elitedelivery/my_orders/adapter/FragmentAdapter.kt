package com.example.elitedelivery.my_orders.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.elitedelivery.my_orders.view.AcceptedDeliveryFragment
import com.example.elitedelivery.my_orders.view.FinishedDeliveryFragment
import com.example.elitedelivery.my_orders.view.PendingDeliveryFragment

class FragmentAdapter (fm : FragmentManager, val fragmentCount : Int): FragmentStatePagerAdapter(fm){

    private val fragmentTitleList = mutableListOf("Accepted", "Pending","Completed")

    override fun getItem(position:Int): Fragment {

        when(position){
            0-> return AcceptedDeliveryFragment()
            1-> return PendingDeliveryFragment()
            2-> return FinishedDeliveryFragment()

            else -> return AcceptedDeliveryFragment()
        }
    }

    override fun getPageTitle(position: Int):CharSequence?{
        return fragmentTitleList[position]
    }
    override fun getCount(): Int = fragmentCount


}