package com.ngjo.bookmanager.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.ngjo.bookmanager.R
import org.w3c.dom.Text

class EditTextWithLabel(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val editText: EditText
    private val textView: TextView
    private val editTextLabel: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_title_edit_text, this, true)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)
        editTextLabel = findViewById(R.id.editTextLabel)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithLabel)
        val labelText = typedArray.getString(R.styleable.EditTextWithLabel_labelText)
        typedArray.recycle()

        textView.text = labelText
    }

    fun setEditTextType(inputType: Int) {
        editText.inputType = inputType
    }


    fun setLabelText(text: String) {
        textView.text = text
    }

    fun setEditText(text: String) {
        editText.setText(text)
        editTextLabel.text = text
    }

    fun getEditTextToString(): String {
        return editText.text.toString()
    }

    fun getEditTextToInt(): Int {
        return editText.text.toString().toInt()
    }

    fun setEditTextEnabled(value: Boolean) {
        editText.isEnabled = value
    }

    fun setCustomClickListener(value: () -> Unit) {
        editTextLabel.setOnClickListener { value() }
    }

    fun changeMode() {
        editText.isVisible = false
        editTextLabel.isVisible = true
    }

    fun getEditTextLabelToString(): String {
        return editTextLabel.text.toString()
    }
}