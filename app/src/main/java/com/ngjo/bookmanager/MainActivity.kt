package com.ngjo.bookmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ngjo.bookmanager.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mFragment = MainFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, mFragment)
            .commit()
    }
}