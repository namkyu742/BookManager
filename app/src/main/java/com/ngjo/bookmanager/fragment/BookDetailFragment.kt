package com.ngjo.bookmanager.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ngjo.bookmanager.data.Book
import com.ngjo.bookmanager.databinding.LayoutBookDetailFragmentBinding
import com.ngjo.bookmanager.viewmodel.BookDetailViewModel

class BookDetailFragment : Fragment() {
    private lateinit var binding: LayoutBookDetailFragmentBinding
    private val viewModel by viewModels<BookDetailViewModel>()

    private var bookDetailListener: BookDetailListener? = null
    private var closeListener: CloseListener? = null

    fun setCloseListener(listener: CloseListener) {
        closeListener = listener
    }

    fun setBookDetailListener(listener: BookDetailListener) {
        bookDetailListener = listener
    }


    companion object {
        fun newInstance(
            book: Book,
            containerId: Int
        ): BookDetailFragment {
            return BookDetailFragment().apply {
//                viewModel.currentBook = book
                arguments = bundleOf(
                    "book" to book,
                    "containerId" to containerId
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = LayoutBookDetailFragmentBinding.inflate(inflater, container, false)

        initViewModel()

        binding.title.text = viewModel.currentBook.title
        binding.price.text = viewModel.currentBook.price.toString()
        binding.number.text = viewModel.currentBook.number.toString()
        binding.date.text = viewModel.currentBook.date

//
//        binding.btnUpdate.setOnClickListener {
//
//
//            val bookInsertFragment = BookInsertFragment.newInstance().apply {
////                setCloseListener(object : CloseListener{
////                    override fun onCloseChildFragment() {
////                        val childFragment = parentFragmentManager.findFragmentById(viewModel.containerId)
////                        childFragment?.let {
////                            parentFragmentManager.beginTransaction()
////                                .remove(childFragment)
////                                .commit()
////                        }
//////                        refreshBookList()
////                    }
////                })
//            }
//
//            parentFragmentManager.beginTransaction()
////            childFragmentManager.beginTransaction()
//                .replace(viewModel.containerId, bookInsertFragment)
//                .addToBackStack(null)
//                .commit()
//
//        }


        binding.btnDelete.setOnClickListener {
            bookDetailListener?.deleteBookByTitle(viewModel.currentBook.title)
            closeListener?.onCloseChildFragment()
        }





        return binding.root
    }

    private fun initViewModel() {
        arguments?.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                viewModel.currentBook = getParcelable("book", Book::class.java) ?: Book()
                viewModel.containerId = getInt("containerId")
            }
        }
    }


}