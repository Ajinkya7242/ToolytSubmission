package com.example.happid.viewModels

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginScreenViewModel : ViewModel() {

    private val _formattedText = MutableLiveData<String>("")
    val formattedText: LiveData<String> get() = _formattedText

    val textWatcher: TextWatcher = object : TextWatcher {
        var isFormatting = false
        var deleteLength = 0

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            deleteLength = count
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            if (isFormatting) return
            isFormatting = true

            // Remove all spaces
            val newText = s.toString().replace(" ", "")

            // Insert single spaces after every digit and double space after the fifth digit
            val formatted = StringBuilder()
            for (i in newText.indices) {
                formatted.append(newText[i])
                if (i == 4) {
                    formatted.append("  ")  // double space after the fifth digit
                } else if (i < newText.length - 1) {
                    formatted.append(" ")
                }
            }

            _formattedText.value = formatted.toString()
            isFormatting = false
        }
    }
}
