package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository

class ProfileViewModel(
    private val repository: UserRepository) : ViewModel() {


    val user = repository.getUser()
}
