package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.AppDatabase
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.Quote
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.MyApi
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.SafeApiRequest
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.preferences.PreferenceProvider
import com.example.mvvmkotlinretrofitroomkodeindatabinding.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6
class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs : PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes() : LiveData<List<Quote>>{

        return withContext(Dispatchers.IO){

            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }

    }
    private suspend fun fetchQuotes() {

        val lastSavedAt = prefs.getlastSavedAt()
        if (lastSavedAt == null || isFetchedNeeded(LocalDateTime.parse(lastSavedAt))) {

            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)

        }
    }
    private fun isFetchedNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now()) > MINIMUM_INTERVAL
    }
    private fun saveQuotes(quotes: List<Quote>) {

        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }
}