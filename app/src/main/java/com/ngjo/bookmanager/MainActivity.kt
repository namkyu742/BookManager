package com.ngjo.bookmanager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ngjo.bookmanager.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rootView = findViewById<View>(R.id.main_container)
        rootView.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
        }





        mFragment = MainFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, mFragment)
            .commit()
    }
}