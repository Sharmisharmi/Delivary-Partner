package com.rocks.fit.api

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.databinding.ktx.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class APIClient : Application() {
    val TAG: String = javaClass.getSimpleName()
    var mInstance: APIClient? = null

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
    companion object{
        fun getRetrofitClient(): Retrofit? {
            var retrofit: Retrofit? = null
//           val LOCAL_BASE_URL = "https://prematix.com/fitness_api/"
//           val LOCAL_BASE_URL = "http://www.prematix.solutions/FitnessApi/"
//           val LOCAL_BASE_URL = "http://192.168.1.39:8030/"
           val LOCAL_BASE_URL = "https://super-mart.onrender.com/"

            if (retrofit == null) {
                var client: OkHttpClient = OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES).build()
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client = OkHttpClient.Builder()
                        .addInterceptor(interceptor).build()
                }
                retrofit = Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(LOCAL_BASE_URL)
                    .build()
            }

            return retrofit
        }
    }

    @Synchronized
    fun getInstance(): APIClient? {
        return mInstance
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val netInfo = connectivityManager.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected
                && netInfo.isConnectedOrConnecting
                && netInfo.isAvailable
            ) {
                return true
            }
        }
        return false
    }
}