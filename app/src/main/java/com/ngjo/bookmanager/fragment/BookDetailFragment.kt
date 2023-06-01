package com.ngjo.bookmanager.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ngjo.bookmanager.databinding.LayoutBookDetailFragmentBinding

class BookDetailFragment : Fragment() {
    private lateinit var binding: LayoutBookDetailFragmentBinding
    lateinit var currentBook: Book

    companion object {
        fun newInstance(
            book: Book
        ): BookDetailFragment {
            return BookDetailFragment().apply {
//                arguments = bundleOf(
//                    "book" to book
//                )
                currentBook = book
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutBookDetailFragmentBinding.inflate(inflater, container, false)

//        Log.d("TOTO", "${arguments}")

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            currentBook = arguments?.getParcelable("book", Book::class.java)?: Book("EMPTY2")
//        }
//
        binding.title.text = currentBook.title
        binding.price.text = currentBook.price.toString()
        binding.number.text = currentBook.number.toString()





        return binding.root
    }


}