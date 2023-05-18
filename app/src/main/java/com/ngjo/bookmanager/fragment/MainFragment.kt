package com.ngjo.bookmanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ngjo.bookmanager.databinding.LayoutMainFragmentBinding


class MainFragment: Fragment() {
    private lateinit var binding: LayoutMainFragmentBinding


    companion object {
        fun newInstance(

        ): MainFragment {
            return MainFragment().apply {
                arguments = bundleOf(

                )
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutMainFragmentBinding.inflate(inflater, container, false)

        initFragment()

        return binding.root
    }


    private fun initFragment() {
        binding.text1.text = "ABCDE"
    }


}