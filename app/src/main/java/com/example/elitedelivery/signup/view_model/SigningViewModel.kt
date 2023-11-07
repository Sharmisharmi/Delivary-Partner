package com.example.elitedelivery.signup.view_model

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.elitedelivery.signup.model.SignInResponse
import com.example.elitedelivery.signup.repository.SigningRepository

class SigningViewModel {

    private var signingRepository:SigningRepository = SigningRepository()

    private var generalsignUp: MutableLiveData<SignInResponse> = MutableLiveData()
    fun generalsignUp(json: String,activity: Activity): MutableLiveData<SignInResponse> {
        generalsignUp = signingRepository.generalsignUp(json,activity)
        return generalsignUp
    }
}