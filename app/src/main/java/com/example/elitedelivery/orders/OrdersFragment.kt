package com.example.elitedelivery.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elitedelivery.databinding.FragmentOrdersBinding
import com.example.elitedelivery.orders.adapter.NewOrdersAdapter
import com.example.elitedelivery.orders.model.NewOrdersResponse
import com.facebook.shimmer.ShimmerFrameLayout


class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding


    var newOrderViewModel: NewOrdersViewModel = NewOrdersViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)




        getNewOrderApi()


        return binding.root


    }

    private fun getNewOrderApi() {
        binding.shimmerViewContainer.startShimmer();
        newOrderViewModel!!.getNewOrderList().observe(this) { res: NewOrdersResponse ->
            if (res.message!!.equals("All orders retrieved successfully", ignoreCase = true)) {

                binding.shimmerViewContainer.stopShimmer()
                binding.newOrdersRecyclerView.visibility = View.VISIBLE

                var data = res.data

                binding.newOrdersRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.newOrdersRecyclerView.adapter = NewOrdersAdapter(activity!!, data)


            }

        }


    }
}