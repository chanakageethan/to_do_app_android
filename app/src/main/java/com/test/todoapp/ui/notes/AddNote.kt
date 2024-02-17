package com.test.todoapp.ui.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.test.todoapp.R
import com.test.todoapp.databinding.ActivityAddNoteBinding

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        binding.etTitle.editText?.addTextChangedListener(textWatcher)
        binding.etNote.editText?.addTextChangedListener(textWatcher)


    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            updateButtonState()
        }

        override fun afterTextChanged(s: Editable) {

        }
    }

    private fun updateButtonState() {
        binding.btnSaveNote.isEnabled = isDataChanged()
    }

    private fun isDataChanged(): Boolean {
        var changed: Boolean
        return try {
            changed =
                !binding.etTitle.editText?.text.toString()
                    .isNullOrEmpty() && !binding.etNote.editText?.text.toString()
                    .isNullOrEmpty()

            changed
        } catch (e: Exception) {
            false
        }
    }


    fun onClick(v: View) {
        if (v.id == R.id.btnSaveNote) {
            Log.d("new_note", binding.etTitle.editText?.text.toString())
            Log.d("new_note", binding.etNote.editText?.text.toString())
        }
    }
}