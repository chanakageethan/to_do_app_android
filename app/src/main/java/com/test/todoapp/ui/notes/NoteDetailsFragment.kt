package com.test.todoapp.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.test.todoapp.R
import com.test.todoapp.databinding.FragmentManageNoteBinding
import com.test.todoapp.databinding.FragmentNoteDetailsBinding


class NoteDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_note_details, container, false)

        val title  = requireArguments().getString("title")
        val note  = requireArguments().getString("note")

        binding?.tvTitle?.text =  title
        binding?.tvNote?.text = note

        return binding.root
    }


}