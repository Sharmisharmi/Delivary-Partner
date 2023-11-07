package com.example.elitedelivery.my_orders.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elitedelivery.R
import com.example.elitedelivery.my_orders.adapter.AcceptedOrdersAdapter


class AcceptedDeliveryFragment : Fragment() {

    lateinit var acceptedOrdersRecyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_accepted_delivery, container, false)

        acceptedOrdersRecyclerView= view.findViewById(R.id.acceptedOrdersRecyclerView)

        getAcceptedDeliveryAPI()



        return view
    }

    private fun getAcceptedDeliveryAPI() {
        acceptedOrdersRecyclerView.layoutManager = LinearLayoutManager(activity)
        acceptedOrdersRecyclerView.adapter = AcceptedOrdersAdapter(activity!!, "Accepted")
    }

}