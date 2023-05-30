package com.ngjo.bookmanager.fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ngjo.bookmanager.databinding.ItemBookBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    var bookList = mutableListOf<Book>()

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

    fun setList(list: MutableList<Book>) {
        bookList.clear()
        list.forEach { book ->
            bookList.add(
                Book(
                    title = book.title
                )
            )
        }
    }


//    fun addItem(item: Book) {
//        bookList.add(item)
////        notifyItemInserted(bookList.size-1)
//        Log.d("TOTO", "${bookList.size}")
//        notifyDataSetChanged()
//    }


    inner class BookViewHolder(
        private val binding: ItemBookBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book, position: Int) {
            binding.title.text = book.title




            binding.title.setOnClickListener {
                Log.d("TOTO", "item Clicked : ${binding.title.text}")
            }
        }
    }
}