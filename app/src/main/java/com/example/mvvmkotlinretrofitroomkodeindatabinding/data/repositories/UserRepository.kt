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

    //for user login
    suspend fun userLogin(email : String, password : String) : AuthResponse{
         return apiRequest{api.userLogin(email,password)}

    }

    //save user
    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()
}