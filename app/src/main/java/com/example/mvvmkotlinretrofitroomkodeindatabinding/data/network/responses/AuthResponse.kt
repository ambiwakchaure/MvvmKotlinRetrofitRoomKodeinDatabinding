package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.responses

import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.User

data class AuthResponse (
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)