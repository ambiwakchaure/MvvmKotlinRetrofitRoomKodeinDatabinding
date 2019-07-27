package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network

import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.responses.AuthResponse
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.responses.QuotesResponse
import okhttp3.OkHttpClient

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi{

    //for login
    @FormUrlEncoded//if we use post request
    @POST("login")
    suspend fun  userLogin(// suspend function paused and resume later time and wait for long running application without blocking
        @Field("email") email : String,//Field name should match with api key name
        @Field("password") password : String
    ) : Response<AuthResponse>

    //for signup
    @FormUrlEncoded//if we use post request
    @POST("signup")
    suspend fun  userSignup(// suspend function paused and resume later time and wait for long running application without blocking
        @Field("name") name : String,//Field name should match with api key name
        @Field("email") email : String,//Field name should match with api key name
        @Field("password") password : String
    ) : Response<AuthResponse>

    @GET("quotes")
    suspend fun getQuotes() : Response<QuotesResponse>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi{

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}