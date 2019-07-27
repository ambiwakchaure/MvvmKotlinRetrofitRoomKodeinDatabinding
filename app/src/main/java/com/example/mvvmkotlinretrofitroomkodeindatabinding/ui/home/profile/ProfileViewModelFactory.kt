package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository

class ProfileViewModelFactory(private val repository: UserRepository)
    : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}