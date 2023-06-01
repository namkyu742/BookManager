package com.ngjo.bookmanager.fragment

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey var id: Int? = null,
    @ColumnInfo var title: String = "dummy data",
    @ColumnInfo var price: Int = 0,
    @ColumnInfo var number: Int = 0,
    @ColumnInfo var date: String = "dummy data",
    @ColumnInfo var etc: String = "dummy data",
)