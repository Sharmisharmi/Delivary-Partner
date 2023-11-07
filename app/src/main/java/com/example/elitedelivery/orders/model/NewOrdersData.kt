package com.example.elitedelivery.orders.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class NewOrdersData {

    @SerializedName("_id")
    @Expose
     val id: String? = null

    @SerializedName("orderID")
    @Expose
     val orderID: String? = null

    @SerializedName("DropLocation")
    @Expose
     val dropLocation: String? = null

    @SerializedName("timestamp")
    @Expose
     val timestamp: String? = null

    @SerializedName("items")
    @Expose
     val items: List<OrdersList>? = null

    @SerializedName("totalAmount")
    @Expose
     val totalAmount: Int? = null

    @SerializedName("paymentStatus")
    @Expose
     val paymentStatus: String? = null

    @SerializedName("__v")
    @Expose
     val v: Int? = null

    @SerializedName("distance")
    @Expose
     val distance: String? = null
}