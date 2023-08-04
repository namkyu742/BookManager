package com.ngjo.bookmanager.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ngjo.bookmanager.databinding.LayoutDatepickerDialogBinding
import java.time.LocalDateTime

class DatePickerDialogFragment : DialogFragment() {

    private lateinit var binding: LayoutDatepickerDialogBinding
    private var datePickerListener: DatePickerListener? = null

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0

    companion object {
        fun newInstance(): DatePickerDialogFragment {
            return DatePickerDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutDatepickerDialogBinding.inflate(inflater, container, false)

        initDatePicker()
        eventListener()

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        datePickerListener = null
    }


    private fun eventListener() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.datePicker.init(mYear, mMonth-1, mDay) { _, year, monthOfYear, dayOfMonth ->
            mYear = year
            mMonth = monthOfYear + 1
            mDay = dayOfMonth
            datePickerListener?.actionConfirm(mYear, mMonth, mDay)
            dismiss()
        }
    }

    private fun initDatePicker() {
        val currentData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        mYear = currentData.year
        mMonth = currentData.monthValue
        mDay = currentData.dayOfMonth
    }

    fun setDatePickerListener(listener: DatePickerListener) {
        this.datePickerListener = listener
    }

}