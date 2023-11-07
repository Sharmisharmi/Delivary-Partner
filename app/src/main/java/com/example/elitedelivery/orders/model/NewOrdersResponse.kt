package com.example.elitedelivery.orders.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NewOrdersResponse {


    @SerializedName("message")
    @Expose
     val message: String? = null

    @SerializedName("data")
    @Expose
     val data: List<NewOrdersData>? = null
}