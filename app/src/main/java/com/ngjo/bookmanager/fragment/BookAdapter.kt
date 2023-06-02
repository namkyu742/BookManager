package com.ngjo.bookmanager.fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngjo.bookmanager.R
import com.ngjo.bookmanager.data.Book
import com.ngjo.bookmanager.databinding.ItemBookBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    var bookList = mutableListOf<Book>()

    var bookListener: BookInterface? = null

    var currentTitle: String = ""

    fun setBookInterface(listener: BookInterface) {
        bookListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position], position)
    }


    inner class BookViewHolder(
        private val binding: ItemBookBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book, position: Int) {
            binding.title.text = book.title



            if (currentTitle == book.title) {
                binding.bookItem.setBackgroundResource(R.drawable.shape_item_book_selected)
            } else {
                binding.bookItem.setBackgroundResource(R.drawable.shape_item_book)
            }





            binding.title.setOnClickListener {
                Log.d("TOTO", "item Clicked : ${binding.title.text}")
                bookListener?.showDetailFragment(binding.title.text.toString())
                bookListener?.setCurrentBookTitle(book.title)
            }
        }
    }

    fun settingCurrentTitle(title: String) {
        currentTitle = title
    }
}

interface BookInterface {
    fun showDetailFragment(title: String)

    fun setCurrentBookTitle(title: String)
}