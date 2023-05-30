package com.ngjo.bookmanager.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngjo.bookmanager.R
import com.ngjo.bookmanager.databinding.LayoutMainFragmentBinding


class MainFragment: Fragment() {
    private lateinit var binding: LayoutMainFragmentBinding

    var bookAdapter: BookAdapter = BookAdapter()
    var bookList = mutableListOf(
        Book(title = "A-가나다라마바사"),
        Book(title = "B-아자차카타파하"),
        Book(title = "C-1234456789")
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



        binding.buttonAddBook.setOnClickListener {
            bookList.add(Book(title = "TEST"))
            bookAdapter.bookList = bookList
            bookAdapter.notifyDataSetChanged()
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


}