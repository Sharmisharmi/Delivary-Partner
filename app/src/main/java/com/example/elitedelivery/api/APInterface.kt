package com.rocks.fit.api

import com.example.elitedelivery.orders.model.NewOrdersResponse
import com.example.elitedelivery.signup.model.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface APInterface {


    @Headers("Content-Type: application/json")
    @POST("Register/create")
    fun generalsignUp(@Body Postdata: String?): Call<SignInResponse?>?


    // New Orders


    @GET("Orders/getAll")
    fun getNewOrdersList(): Call<NewOrdersResponse?>?



}