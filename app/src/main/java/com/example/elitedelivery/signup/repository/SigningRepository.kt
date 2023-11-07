package com.example.elitedelivery.signup.repository

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.elitedelivery.signup.model.SignInResponse
import com.rocks.fit.api.APIClient
import com.rocks.fit.api.APInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigningRepository {

    fun generalsignUp(json: String,activity: Activity): MutableLiveData<SignInResponse> {
        val mutableLiveData: MutableLiveData<SignInResponse> = MutableLiveData()
        val apiService: APInterface? =
            APIClient.getRetrofitClient()?.create(APInterface::class.java)
        apiService?.generalsignUp(json)?.enqueue(object : Callback<SignInResponse?> {
            override fun onResponse(
                call: Call<SignInResponse?>?,
                response: Response<SignInResponse?>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("response_otp", "onResponse: " + response.raw())
                    mutableLiveData.setValue(response.body()!!)
                } else {
                    Log.i("Errorass", response.toString())
                }
            }

            public override fun onFailure(call: Call<SignInResponse?>, t: Throwable?) {
                Log.i("Error", "" + call.request().toString().trim() + " " + t)
            }
        })
        return mutableLiveData
    }
}