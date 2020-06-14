package com.sachtech.datingapp.cometChat.network

import com.sachtech.datingapp.data.*
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiInterface {


    @POST("users")
    fun createCometUser(
        @Header("content-type") content: String = "application/json",
        @Header("accept") accept: String = "application/json",
        @Header("appid") appId: String,
        @Header("apikey") apikey: String,
        @Body chatUser: ChatUser
    ): Single<CreateUserResponse>

    @POST("users/{UID}/friends")
    fun addFriend(
        @Header("Content-type") content: String = "application/json",
        @Header("accept") accept: String = "application/json",
        @Header("appid") appId: String,
        @Header("apikey") apikey: String,
        @Path("UID") uid: String,
        @Body friend: Friend
    ): Single<AddFriendResponse>

    @GET("users/{UID}/friends")
    fun getFriends(
        @Header("content-type") content: String = "application/json",
        @Header("accept") accept: String = "application/json",
        @Header("appid") appId: String,
        @Header("apikey") apikey: String,
        @Path("UID")uid:String
    ): Single<GetFriends>

    @HTTP(method = "DELETE", path = "users/{UID}/friends", hasBody = true)
    fun deleteFriend(
        @Header("content-type") content: String = "application/json",
        @Header("accept") accept: String = "application/json",
        @Header("appid") appId: String,
        @Header("apikey") apikey: String,
        @Path("UID") uid: String,
        @Body friend: RemoveFriendList
    ): Single<DeleteFriend>

    @HTTP(method = "DELETE", path = "users/{uid}", hasBody = true)
    fun deleteUser(
        @Header("content-type") content: String = "application/json",
        @Header("accept") accept: String = "application/json",
        @Header("appid") appId: String,
        @Header("apikey") apikey: String,
        @Path("uid") uid: String
    ): Single<DeleteUser>
    @GET
    fun downloadFbPicture(@Url fileUrl: String): Single<ResponseBody>


    @PUT("users/{uid}")
    fun updateUserOnCometChat(
        @Header("content-type") content: String = "application/json",
        @Header("accept") accept: String = "application/json",
        @Header("appid") appId: String,
        @Header("apikey") apikey: String,
        @Path("uid") uid: String,
        @Body chatUser: ChatUser
    ):Single<UpdateResponse>
}

