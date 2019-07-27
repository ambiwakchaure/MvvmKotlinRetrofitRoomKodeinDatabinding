package com.example.mvvmkotlinretrofitroomkodeindatabinding

import android.app.Application
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.AppDatabase
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.MyApi
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.NetworkConnectionInterceptor
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.preferences.PreferenceProvider
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.QuotesRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.repositories.UserRepository
import com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.auth.AuthViewModelFactory
import com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.profile.ProfileViewModelFactory
import com.example.mvvmkotlinretrofitroomkodeindatabinding.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MyApplication))

        //create instance using codein framework
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { QuotesRepository(instance(),instance(),instance()) }
        bind() from singleton { AuthViewModelFactory(instance()) }
        bind() from singleton { ProfileViewModelFactory(instance()) }
        bind() from singleton { QuotesViewModelFactory(instance()) }
    }
}