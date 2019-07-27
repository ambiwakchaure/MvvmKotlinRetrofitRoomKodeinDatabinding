package com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.Quote
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.db.entities.User


@Database(
    entities = [User::class , Quote::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao

    //create app database
    companion object {

        //instane of app database
        @Volatile//because immediatly viewd to other threads
        private var instance: AppDatabase? = null
        private val LOCk = Any()

        //check instance is null
        operator fun invoke(context: Context) = instance ?: synchronized(LOCk) {
            //check instance not null if then build database
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        //create database
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()

    }
}