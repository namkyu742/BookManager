package com.ngjo.bookmanager.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ngjo.bookmanager.fragment.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAll(): List<Book>

    @Insert
    fun insertBook(book: Book)
}