package com.example.elitedelivery.my_orders.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elitedelivery.databinding.FragmentPendingDeliveryBinding
import com.example.elitedelivery.my_orders.adapter.AcceptedOrdersAdapter


class PendingDeliveryFragment : Fragment() {

    lateinit var binding: FragmentPendingDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPendingDeliveryBinding.inflate(inflater,container,false)

        getPendingOrdersAPI()
        return binding.root
    }

    private fun getPendingOrdersAPI() {
        binding.pendingOrdersRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.pendingOrdersRecyclerView.adapter = AcceptedOrdersAdapter(activity!!,"Pending")
    }

}