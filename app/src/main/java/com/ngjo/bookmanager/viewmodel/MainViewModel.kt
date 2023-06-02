package com.ngjo.bookmanager.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var isShow = false

    fun toggleIsShow() {
        this.isShow = !this.isShow
    }


    var currentBookTitle = ""
}