package com.sachtech.datingapp.networking


import com.sachtech.datingapp.R
import com.sachtech.datingapp.app.DatingApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class VerifyApi {

    var accept="application/json"
    fun createService(): ApiInterface {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(60000, TimeUnit.MILLISECONDS)
            .readTimeout(60000, TimeUnit.MILLISECONDS)
            .writeTimeout(60000, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(client.build())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    companion object {
        val key="key=${DatingApp.application?.resources?.getString(R.string.firebase_server_key)}"
        private const val API_BASE_URL = "https://stsmentor.com/Rnikah/"
        val instance by lazy { VerifyApi().createService() }
        fun getService() = instance
    }
}

fun apiHitVerify(): ApiInterface {
    return VerifyApi.getService()
}
