package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.QuotesRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository

class QuotesViewModelFactory(private val repository: QuotesRepository)
    : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}