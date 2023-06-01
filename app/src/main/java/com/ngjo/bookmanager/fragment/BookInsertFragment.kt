package com.ngjo.bookmanager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ngjo.bookmanager.databinding.LayoutBookInsertFragmentBinding

class BookInsertFragment : Fragment() {
    private lateinit var binding: LayoutBookInsertFragmentBinding

    private var closeListener: CloseListener? = null

    fun setCloseListener(listener: CloseListener) {
        closeListener = listener
    }

    companion object {
        fun newInstance(): BookInsertFragment {
            return BookInsertFragment().apply {

            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CloseListener) {
            closeListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        closeListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutBookInsertFragmentBinding.inflate(inflater, container, false)

        // 현재 날짜로 init하기
        binding.datePicker.init(2023, 5, 1) { view, year, monthOfYear, dayOfMonth ->
            Log.d("TOTO", "date: $year/$monthOfYear/$dayOfMonth : $view")
        }






        binding.btnCancel.setOnClickListener {
            Log.d("TOTO", "cancel")
            Log.d("TOTO", "listener: $closeListener")
            closeListener?.onCloseChildFragment()
        }

        binding.btnConfirm.setOnClickListener {
            Log.d("TOTO", "confirm")
            childFragmentManager.popBackStack()
        }


        return binding.root
    }


}