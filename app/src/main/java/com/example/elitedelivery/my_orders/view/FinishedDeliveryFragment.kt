package com.example.elitedelivery.my_orders.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elitedelivery.R
import com.example.elitedelivery.databinding.FragmentFinishedDeliveryBinding
import com.example.elitedelivery.my_orders.adapter.AcceptedOrdersAdapter


class FinishedDeliveryFragment : Fragment() {

    private lateinit var binding:FragmentFinishedDeliveryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinishedDeliveryBinding.inflate(inflater,container,false)

        getFinishedOrderAPI()
        return binding.root
    }

    private fun getFinishedOrderAPI() {
        binding.finishedDeliveryRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.finishedDeliveryRecyclerView.adapter = AcceptedOrdersAdapter(activity!!,"Finished")
    }

}