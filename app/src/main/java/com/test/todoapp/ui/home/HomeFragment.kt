package com.test.todoapp.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.todoapp.R
import com.test.todoapp.data.model.Note
import com.test.todoapp.databinding.FragmentHomeBinding
import com.test.todoapp.ui.notes.NotesAdapter


class HomeFragment : Fragment(){

    private lateinit var binding :FragmentHomeBinding

    val notesList = listOf<Note>(
        Note("list title 1","sample Note"),
        Note("list title 2","sample Note"),
        Note("list title 3","sample Note"),
        Note("list title 4","sample Note"),
        Note("list title 5","sample Note"),
        Note("list title 6","sample Note"),
        Note("list title 7","sample Note"),
        Note("list title 8","sample Note"),
        Note("list title 9","sample Note"),
        Note("list title 10","sample Note"),
        Note("list title 11","sample Note"),
        Note("list title 12","sample Note"),
        Note("list title 13","sample Note"),
        Note("list title 14","sample Note"),
        Note("list title 15","sample Note"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)



        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerView?.adapter =  NotesAdapter(
            notesList,
        ){selectedItem:Note ->
            listItemClicked(selectedItem)

        }



        binding.addNote.setOnClickListener {
            navigateToManageNoteFragment()
        }

        return  binding.root
    }


    private fun listItemClicked(note: Note){
        Toast.makeText(
            context,
            "Note : ${note.note}",
            Toast.LENGTH_LONG
        ).show()

        navigateToNoteDetailsFragment()
    }


    private fun navigateToManageNoteFragment(){
        val bundle: Bundle = bundleOf("is_edit" to false)
        findNavController().navigate(
            R.id.action_homeFragment_to_manageNoteFragment,
            bundle
        )
    }

    private fun navigateToNoteDetailsFragment(){
        val bundle: Bundle = bundleOf("note" to "test")
        findNavController().navigate(
            R.id.action_homeFragment_to_noteDetailsFragment,
            bundle
        )
    }

}