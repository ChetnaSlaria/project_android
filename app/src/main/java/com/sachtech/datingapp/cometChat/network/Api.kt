package com.sachtech.datingapp.cometChat.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    var accept="application/json"
    fun createService(): ApiInterface {
      /*  val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .connectTimeout(60000, TimeUnit.MILLISECONDS)
            .readTimeout(60000, TimeUnit.MILLISECONDS)
            .writeTimeout(60000, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)*/

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
          /*  .client(client.build())*/
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    companion object {
        private const val API_BASE_URL = "https://api-us.cometchat.io/v2.0/"
        val instance by lazy { Api().createService() }
        fun getService() = instance
    }
}

fun apiHitter(): ApiInterface {
    return Api.getService()
}
