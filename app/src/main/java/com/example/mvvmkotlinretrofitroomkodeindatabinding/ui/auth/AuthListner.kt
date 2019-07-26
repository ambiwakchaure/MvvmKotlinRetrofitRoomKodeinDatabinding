package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.User

interface AuthListner {
    fun onStarted()
    fun onSuccess(user : User)
    fun onFailure(message : String)
}