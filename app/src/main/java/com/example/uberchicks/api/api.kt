package com.example.uberchicks.api

import com.example.uberchicks.domain.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface api {
    @FormUrlEncoded
    @POST("createUser")
    fun createUser(
        @Field("email") email:String,
        @Field("first_name") first_name:String,
        @Field("last_name") last_name:String,
        @Field("password") password:String,

        ): Call<DefaultResponse>
}