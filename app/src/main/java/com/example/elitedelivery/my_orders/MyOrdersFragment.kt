package com.example.elitedelivery.my_orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.elitedelivery.R
import com.example.elitedelivery.my_orders.adapter.FragmentAdapter
import com.google.android.material.tabs.TabLayout


class MyOrdersFragment : Fragment() {

    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout
//    private lateinit var fragment_container: FrameLayout  // creating object of TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_my_orders, container, false)

        pager = view!!.findViewById(R.id.viewPager)
        tab = view!!.findViewById(R.id.tabs)
//        fragment_container = view!!.findViewById(R.id.fragment_container)

        configureTopNavigation()





        return view
    }

    private fun configureTopNavigation() {

        pager.adapter = FragmentAdapter(childFragmentManager, 3)

        pager.offscreenPageLimit = 3
        tab.setupWithViewPager(pager)
    }

}