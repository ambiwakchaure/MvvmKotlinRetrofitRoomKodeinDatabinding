package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.responses

import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful : Boolean,
    val quotes : List<Quote>

)