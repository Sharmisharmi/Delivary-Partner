package com.example.elitedelivery.orders

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.elitedelivery.orders.model.NewOrdersResponse
import com.example.elitedelivery.orders.repository.NewOrdersRepository

class NewOrdersViewModel {


    var newOrdersRepository:NewOrdersRepository = NewOrdersRepository()

    private var getNewOrdersData: MutableLiveData<NewOrdersResponse> = MutableLiveData()
    fun getNewOrderList(
    ): MutableLiveData<NewOrdersResponse> {
        getNewOrdersData = newOrdersRepository!!.requestGetNewOrdersList()
        return getNewOrdersData
    }

}