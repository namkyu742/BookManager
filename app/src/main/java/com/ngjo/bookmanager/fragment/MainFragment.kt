package com.ngjo.bookmanager.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngjo.bookmanager.Database
import com.ngjo.bookmanager.data.Book
import com.ngjo.bookmanager.databinding.LayoutMainFragmentBinding
import com.ngjo.bookmanager.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment: Fragment() {
    private lateinit var binding: LayoutMainFragmentBinding
    private val viewModel by viewModels<MainViewModel>()

    private var bookAdapter: BookAdapter = BookAdapter()
    private var bookList = mutableListOf<Book>()

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
//        initSampleData()
        initSearchBar()
        initDatabase()


        bookAdapter.setBookInterface(object : BookInterface {
            override fun showDetailFragment(title: String) {
                showDetailFragment1(title)
            }

        })

        binding.buttonAddBook.setOnClickListener {

            val bookInsertFragment = BookInsertFragment.newInstance().apply {
                setCloseListener(object: CloseListener {
                    override fun onCloseChildFragment() {
                        val childFragment = parentFragmentManager.findFragmentById(binding.fragmentContainerView.id)
                        childFragment?.let {
                            parentFragmentManager.beginTransaction()
                                .remove(childFragment)
                                .commit()
                        }
                        refreshBookList()
                    }
                })
            }

            childFragmentManager.beginTransaction()
                .replace(binding.fragmentContainerView.id, bookInsertFragment)
                .addToBackStack(null)
                .commit()

        }

        binding.buttonTest.setOnClickListener {
            refreshBookList()
        }


        binding.btnToggleList.setOnClickListener {
            showBookList(isShow = viewModel.isShow)
            viewModel.toggleIsShow()
        }

        return binding.root
    }

    private fun refreshBookList() {
        Log.d("TOTO", "refresh!")
        CoroutineScope(Dispatchers.Default).launch {
            bookList = Database.database.bookDao().getAll().toMutableList()
            bookAdapter.bookList = bookList

            withContext(Dispatchers.Main) {
                bookAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun initDatabase() {
        Database.initDatabase(requireContext())
        refreshBookList()
    }
    private fun showBookList(isShow: Boolean) {
        if(isShow) {
            val anim = TranslateAnimation(-500f, 0f, 0f, 0f)
            anim.duration = 400
            anim.fillAfter = true
            binding.layoutLeft.visibility = View.VISIBLE
            binding.layoutLeft.animation = anim
        } else {
            val anim = TranslateAnimation(0f, -500f, 0f, 0f)
            anim.duration = 400
            anim.fillAfter = false
            binding.layoutLeft.animation = anim
            binding.layoutLeft.visibility = View.GONE
        }
    }

    private fun initSampleData() {
        bookList.add(Book(id = 0, title = "A-가나다라마바사", price = 6800, number = 0))
        bookList.add(Book(id = 1, title = "B-아자차카타파하", price = 7800, number = 3))
        bookList.add(Book(id = 2, title = "C-1234456789", price = 4800, number = 2))
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
            .commit()

    }
}