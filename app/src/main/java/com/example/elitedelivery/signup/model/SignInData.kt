package com.example.elitedelivery.signup.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SignInData {
    @SerializedName("_id")
    @Expose
     val id: String? = null

    @SerializedName("phone")
    @Expose
     val phone: String? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("latitude")
    @Expose
     val latitude: Double? = null

    @SerializedName("longitude")
    @Expose
     val longitude: Double? = null

    @SerializedName("vehicleType")
    @Expose
     val vehicleType: String? = null

    @SerializedName("vehicleName")
    @Expose
     val vehicleName: String? = null

    @SerializedName("vehicleNumber")
    @Expose
     val vehicleNumber: String? = null

    @SerializedName("documentImage")
    @Expose
     val documentImage: String? = null

    @SerializedName("panImage")
    @Expose
     val panImage: String? = null

    @SerializedName("aadharImage")
    @Expose
     val aadharImage: String? = null

    @SerializedName("drivingLicenseImage")
    @Expose
     val drivingLicenseImage: String? = null

}
