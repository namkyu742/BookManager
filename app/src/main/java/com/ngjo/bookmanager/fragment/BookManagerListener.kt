package com.ngjo.bookmanager.fragment

interface CloseListener {
    fun onCloseChildFragment()
}

interface DatePickerListener {
    fun actionConfirm(year: Int, month: Int, day: Int)
}

interface BookDetailListener {
    fun deleteBookByTitle(title: String)
}