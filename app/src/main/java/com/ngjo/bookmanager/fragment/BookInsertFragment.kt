package com.ngjo.bookmanager.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ngjo.bookmanager.Database
import com.ngjo.bookmanager.data.Book
import com.ngjo.bookmanager.databinding.LayoutBookInsertFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

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

        val currentData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val mYear: Int = currentData.year
        val mMonth: Int = currentData.monthValue-1
        val mDay: Int = currentData.dayOfMonth
        var date = ""

        binding.datePicker.init(mYear, mMonth, mDay) { view, year, monthOfYear, dayOfMonth ->
            Log.d("TOTO", "date: $year/${monthOfYear + 1}/$dayOfMonth : $view")
            Log.d("TOTO", "time: ${currentData.hour}:${currentData.minute}:${currentData.second}")
            date = "$year/${monthOfYear + 1}/$dayOfMonth"
        }

        binding.title.setLabelText("TITLE")
        binding.title.setEditText("dummy data")
        binding.title.setEditTextType(InputType.TYPE_CLASS_TEXT)

        binding.price.setLabelText("PRICE")
        binding.price.setEditText("0")
        binding.price.setEditTextType(InputType.TYPE_CLASS_NUMBER)

        binding.number.setLabelText("NUMBER")
        binding.number.setEditText("0")
        binding.number.setEditTextType(InputType.TYPE_CLASS_NUMBER)


        binding.btnCancel.setOnClickListener {
            Log.d("TOTO", "cancel")
            closeListener?.onCloseChildFragment()
        }

        binding.btnConfirm.setOnClickListener {
            Log.d("TOTO", "confirm")
            // 현재시각도 넣을 것
            CoroutineScope(Dispatchers.Default).launch {
                Database.database.bookDao().insertBook(
                    Book(
                        title = binding.title.getEditTextToString(),
                        price = binding.price.getEditTextToInt(),
                        number = binding.number.getEditTextToInt(),
                        date = date
                    )
                )
                withContext(Dispatchers.Main) {
                    closeListener?.onCloseChildFragment()
                }
            }


        }


        return binding.root
    }


}