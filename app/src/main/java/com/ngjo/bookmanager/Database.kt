package com.ngjo.bookmanager

import android.content.Context
import androidx.room.Room
import com.ngjo.bookmanager.data.AppDatabase

object Database {

    lateinit var database: AppDatabase


    fun initDatabase(context: Context) {
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "test_database-book2"
        ).build()
    }

}