package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories

import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.AppDatabase
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.User
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.MyApi
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.SafeApiRequest
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api : MyApi,
    private val db : AppDatabase) : SafeApiRequest(){

    //Api calls goes here
    //for user login
    suspend fun userLogin(email : String, password : String) : AuthResponse{
         return apiRequest{api.userLogin(email,password)}
    }

    //for user signup
    suspend fun userSignup(name : String,email : String, password : String) : AuthResponse{
        return apiRequest{api.userSignup(name,email,password)}
    }

    //database  calles goes here
    //save user
    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)
    fun getUser() = db.getUserDao().getuser()
}