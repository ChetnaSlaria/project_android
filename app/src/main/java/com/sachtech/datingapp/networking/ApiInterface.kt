package com.sachtech.datingapp.networking


import com.google.firebase.messaging.RemoteMessage
import com.sachtech.datingapp.data.CommonResponse
import com.sachtech.datingapp.data.Country
import com.sachtech.datingapp.data.FirebaseNotification
import com.sachtech.datingapp.data.FirebaseNotificationResponse
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

interface ApiInterface {


    @POST("send")
    fun sendNotification(
        @Header("Authorization")key:String=Api.key,
        @Body fcNotification: FirebaseNotification
    ):Single<FirebaseNotificationResponse>


    @FormUrlEncoded
    @POST("sendVerificationMail")
    fun sendVerificationMail(
        @Field("API_KEY")apiKey:String="123456789",
        @Field(" user_name")username:String,
        @Field("user_profile")userProfile:String,
        @Field("user_email")userEmail:String,
        @Field("uuid")uuid:String
    ):Single<CommonResponse>

}