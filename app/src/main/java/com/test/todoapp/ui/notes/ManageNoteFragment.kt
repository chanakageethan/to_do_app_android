package com.test.todoapp.ui.notes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.test.todoapp.R
import com.test.todoapp.databinding.FragmentManageNoteBinding
import com.test.todoapp.utils.IUtils


class ManageNoteFragment : Fragment() {
    private lateinit var binding: FragmentManageNoteBinding
    private   var isEdit : Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage_note, container, false)

        isEdit = requireArguments().getBoolean("is_edit")
        Log.d("ManageNoteFragment:","isEdit>"+isEdit)

        binding.etTitle.editText?.addTextChangedListener(textWatcher)
        binding.etNote.editText?.addTextChangedListener(textWatcher)

        binding.btnSaveNote.setOnClickListener {
            Log.d("new_note", binding.etTitle.editText?.text.toString())
            Log.d("new_note", binding.etNote.editText?.text.toString())

        }


        return binding.root

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



}