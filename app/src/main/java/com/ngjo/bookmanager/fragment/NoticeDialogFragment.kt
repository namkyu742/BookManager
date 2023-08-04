package com.ngjo.bookmanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ngjo.bookmanager.databinding.LayoutNoticeDialogBinding
import com.ngjo.bookmanager.dialogFragmentResize

class NoticeDialogFragment(
    private val title: String,
    private val content: String
) : DialogFragment() {
    private lateinit var binding: LayoutNoticeDialogBinding

    companion object {
        fun newInstance(
            title: String,
            content: String
        ): NoticeDialogFragment {
            return NoticeDialogFragment(title, content)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutNoticeDialogBinding.inflate(inflater, container, false)

        binding.dialogTitle.text = title
        binding.content.text = content

        binding.btnDismiss.setOnClickListener {
            this@NoticeDialogFragment.dismiss()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@NoticeDialogFragment, 0.3f, 0.3f)
    }

}
