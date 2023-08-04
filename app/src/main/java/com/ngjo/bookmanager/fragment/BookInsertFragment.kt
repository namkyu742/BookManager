package com.ngjo.bookmanager.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.ngjo.bookmanager.Database
import com.ngjo.bookmanager.data.Book
import com.ngjo.bookmanager.databinding.LayoutBookInsertFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutBookInsertFragmentBinding.inflate(inflater, container, false)

        binding.title.setLabelText("TITLE")
        binding.title.setEditTextType(InputType.TYPE_CLASS_TEXT)

        binding.price.setLabelText("PRICE")
        binding.price.setEditTextType(InputType.TYPE_CLASS_NUMBER)

        binding.number.setLabelText("NUMBER")
        binding.number.setEditTextType(InputType.TYPE_CLASS_NUMBER)

        binding.date.setLabelText("DATE")
        binding.date.changeMode()
        binding.date.setCustomClickListener {
            val newFragment = DatePickerDialogFragment.newInstance().apply {
                setDatePickerListener(object: DatePickerListener{
                    override fun actionConfirm(year: Int, month: Int, day: Int) {
                        val formattedYear = "$year"
                        var formattedMonth = "$month"
                        var formattedDay = "$day"

                        if(month<10) formattedMonth = "0$month"
                        if(day<10) formattedDay = "0$day"

                        val date = "${formattedYear}년 ${formattedMonth}월 ${formattedDay}일"
                        binding.date.setEditText(date)
                    }
                })
            }
            newFragment.show(childFragmentManager, "")
        }

        val currentDate = LocalDateTime.now()
        val currentYear = currentDate.year
        val currentMonth = if(currentDate.monthValue<10) "0${currentDate.monthValue}" else "${currentDate.monthValue}"
        val currentDay = if(currentDate.dayOfMonth<10) "0${currentDate.dayOfMonth}" else "${currentDate.dayOfMonth}"
        binding.title.setEditText("dummy data")
        binding.price.setEditText("0")
        binding.number.setEditText("0")
        binding.date.setEditText("${currentYear}년 ${currentMonth}월 ${currentDay}일")


        binding.btnCancel.setOnClickListener {
            closeListener?.onCloseChildFragment()
        }

        binding.btnConfirm.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                val temp = Database.database.bookDao().selectBookByTitle(binding.title.getEditTextToString())

                if (temp == null) {
                    Database.database.bookDao().insertBook(
                        Book(
                            title = binding.title.getEditTextToString(),
                            price = binding.price.getEditTextToInt(),
                            number = binding.number.getEditTextToInt(),
                            date = binding.date.getEditTextLabelToString()
                        )
                    )
                    withContext(Dispatchers.Main) {
                        closeListener?.onCloseChildFragment()
                    }
                }
                else {
                    NoticeDialogFragment.newInstance(
                        title = "ALERT",
                        content = "중복입니다.\n"
                    ).show(childFragmentManager, "")
                }
            }
        }
        return binding.root
    }




}