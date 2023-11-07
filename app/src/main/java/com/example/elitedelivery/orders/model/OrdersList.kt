package com.example.elitedelivery.orders.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class OrdersList {


    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("price")
    @Expose
     val price: Int? = null

    @SerializedName("quantity")
    @Expose
     val quantity: Int? = null

    @SerializedName("_id")
    @Expose
     val id: String? = null

}
