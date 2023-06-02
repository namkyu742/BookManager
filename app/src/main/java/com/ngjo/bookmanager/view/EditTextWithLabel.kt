package com.ngjo.bookmanager.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.ngjo.bookmanager.R

class EditTextWithLabel(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val editText: EditText
    private val textView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_title_edit_text, this, true)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithLabel)
        val labelText = typedArray.getString(R.styleable.EditTextWithLabel_labelText)
        typedArray.recycle()

        textView.text = labelText
    }

    fun setLabelText(text: String) {
        textView.text = text
    }

    fun setEditText(text: String) {
        editText.setText(text)
    }

    fun getEditText(): String {
        return editText.text.toString()
    }
}