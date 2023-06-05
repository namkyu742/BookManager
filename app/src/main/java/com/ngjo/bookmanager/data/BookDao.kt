package com.ngjo.bookmanager.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAll(): List<Book>

    @Insert
    fun insertBook(book: Book)

    @Query("DELETE FROM books WHERE title = :title")
    fun deleteBookByTitle(title: String)

    @Query("SELECT * FROM books WHERE title = :title")
    fun selectBookByTitle(title: String) : Book
}