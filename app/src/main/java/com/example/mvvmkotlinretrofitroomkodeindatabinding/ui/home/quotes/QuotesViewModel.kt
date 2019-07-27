package com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.QuotesRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.lazyDeferred

class QuotesViewModel(private val repository: QuotesRepository)
    : ViewModel() {


    val quotes by lazyDeferred {

        repository.getQuotes()

    }
}
