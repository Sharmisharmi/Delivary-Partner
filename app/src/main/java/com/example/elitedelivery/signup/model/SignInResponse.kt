package com.example.elitedelivery.signup.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SignInResponse {


    @SerializedName("message")
    @Expose
     val message: String? = null

    @SerializedName("data")
    @Expose
     val data: SignInData? = null
}