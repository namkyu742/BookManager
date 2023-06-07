package com.ngjo.bookmanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ngjo.bookmanager.databinding.LayoutNoticeDialogBinding
import com.ngjo.bookmanager.dialogFragmentResize

class NoticeDialogFragment : DialogFragment() {
    private lateinit var binding: LayoutNoticeDialogBinding

    companion object {
        fun newInstance(): NoticeDialogFragment {
            return NoticeDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutNoticeDialogBinding.inflate(inflater, container, false)

        binding.dialogTitle.text = "ALERT"
        binding.content.text = "중복입니다."
        // commit test2

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@NoticeDialogFragment, 0.4f, 0.4f)
    }

}
