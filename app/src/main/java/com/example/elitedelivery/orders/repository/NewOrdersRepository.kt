package com.example.elitedelivery.orders.repository

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.elitedelivery.orders.model.NewOrdersResponse
import com.rocks.fit.api.APIClient
import com.rocks.fit.api.APInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewOrdersRepository {


    fun requestGetNewOrdersList(
    ): MutableLiveData<NewOrdersResponse> {
        val mutableLiveData: MutableLiveData<NewOrdersResponse> = MutableLiveData()

        val apiService: APInterface? = APIClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.getNewOrdersList()?.enqueue(object :
            Callback<NewOrdersResponse?> {
            override fun onResponse(call: Call<NewOrdersResponse?>?, response: Response<NewOrdersResponse?>) {
                Log.d("requestTargetFoodList", "onResponse: "+response.raw())
                if (response.isSuccessful && response.body() != null) {
                    mutableLiveData.setValue(response.body()!! )
                }else{

                }
            }
            public override fun onFailure(call: Call<NewOrdersResponse?>, t: Throwable?) {

            }
        })

        return mutableLiveData
    }

}