package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network

import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.responses.AuthResponse

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi{

    @FormUrlEncoded//if we use post request
    @POST("login")
    suspend fun  userLogin(// suspend function paused and resume later time and wait for long running application without blocking
        @Field("email") email : String,//Field name should match with api key name
        @Field("password") password : String
    ) : Response<AuthResponse>


    companion object{
        operator fun invoke() : MyApi{
            return Retrofit.Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}