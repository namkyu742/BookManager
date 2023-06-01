package com.ngjo.bookmanager.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ngjo.bookmanager.data.AppDatabase
import com.ngjo.bookmanager.databinding.LayoutMainFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainFragment: Fragment() {
    private lateinit var binding: LayoutMainFragmentBinding

    private lateinit var database: AppDatabase

    var bookAdapter: BookAdapter = BookAdapter()
    var bookList = mutableListOf(
        Book(id = 0, title = "A-가나다라마바사", price = 6800, number = 0),
        Book(id = 1, title = "B-아자차카타파하", price = 7800, number = 3),
        Book(id = 2, title = "C-1234456789", price = 4800, number = 2)
    )

    companion object {
        fun newInstance(

        ): MainFragment {
            return MainFragment().apply {
                arguments = bundleOf(

                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutMainFragmentBinding.inflate(inflater, container, false)

        initFragment()
        initSampleData()
        initSearchBar()

        database = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "test_database-book1"
        ).build()


        bookAdapter.setBookInterface(object: BookInterface{
            override fun showDetailFragment(title: String) {
                showDetailFragment1(title)
            }

        })

        binding.buttonAddBook.setOnClickListener {
//            bookList.add(Book(title = "TEST"))
//            bookAdapter.bookList = bookList
//            bookAdapter.notifyDataSetChanged()




//            CoroutineScope(Dispatchers.Default).launch {
//                database.bookDao().insertBook(Book(
//                    title = "dbtest"
//                ))
//                bookList = database.bookDao().getAll().toMutableList()
//                bookAdapter.bookList = bookList
//            }
//            bookAdapter.notifyDataSetChanged()



            val bookInsertFragment = BookInsertFragment.newInstance().apply {
                setCloseListener(object: CloseListener {
                    override fun onCloseChildFragment() {
                        val childFragment = parentFragmentManager.findFragmentById(binding.fragmentContainerView.id)
                        Log.d("TOTO", "childFragment2 : $childFragment")
                        if (childFragment != null) {
                            parentFragmentManager.beginTransaction()
                                .remove(childFragment)
                                .commit()
                        }
                    }

                })
            }
            Log.d("TOTO", "childFragment1 : $bookInsertFragment")
            childFragmentManager.beginTransaction()
                .replace(binding.fragmentContainerView.id, bookInsertFragment)
                .addToBackStack(null)
                .commit()

        }

        return binding.root
    }

    private fun initSampleData() {
        bookAdapter.bookList = bookList
        bookAdapter.notifyDataSetChanged()
    }

    private fun initSearchBar() {
        binding.searchText.addTextChangedListener {
            val searchedList = mutableListOf<Book>()
            bookList.forEach {
                if(it.title.contains(binding.searchText.text)){
                    searchedList.add(it)
                }
            }
            bookAdapter.bookList = searchedList
            bookAdapter.notifyDataSetChanged()
        }
    }


    private fun initFragment() {
        binding.recyclerView1.adapter = bookAdapter
        binding.recyclerView1.layoutManager = LinearLayoutManager(this@MainFragment.requireContext())
    }


    fun showDetailFragment1(title: String) {
        var tFragment = BookDetailFragment.newInstance(Book()) // 비효율적. 수정필요
        bookList.forEach {
            if (it.title == title) {
                tFragment = BookDetailFragment.newInstance(it)

            }
        }
        childFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, tFragment)
            .addToBackStack(null)
            .commit()

    }
}