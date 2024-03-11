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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.todoapp.R
import com.test.todoapp.data.local_data.NotesDatabase
import com.test.todoapp.data.model.Note
import com.test.todoapp.databinding.FragmentHomeBinding
import com.test.todoapp.repository.NotesRepository
import com.test.todoapp.ui.notes.NotesAdapter


class HomeFragment : Fragment(){

    private lateinit var binding :FragmentHomeBinding
    private lateinit var notesViewModel: NotesViewModel
    var testData = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."

    val notesList = listOf<Note>(
        Note("list title 1",testData),
        Note("list title 2",testData),
        Note("list title 3",testData),
        Note("list title 4",testData),
        Note("list title 5",testData),
        Note("list title 6",testData),
        Note("list title 7",testData),
        Note("list title 8",testData),
        Note("list title 9",testData),
        Note("list title 10",testData),
        Note("list title 11",testData),
        Note("list title 12",testData),
        Note("list title 13",testData),
        Note("list title 14",testData),
        Note("list title 15",testData),
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


        val dao = NotesDatabase.getInstance(requireContext()).notesDAO
        val repository =  NotesRepository(dao)
        val factory =  NotesViewModelFactory(repository)
        notesViewModel = ViewModelProvider(this, factory!!)[NotesViewModel::class.java]
        binding.myViewModel = notesViewModel
        binding.lifecycleOwner = this
        displaySubscribersList()


        binding.addNote.setOnClickListener {
            navigateToManageNoteFragment()
        }

        return  binding.root
    }


    private fun displaySubscribersList(){
        notesViewModel.notes.observe(viewLifecycleOwner, Observer {
            Log.i("MYTAG",it.toString())
        })
    }


    private fun listItemClicked(note: Note){
        Toast.makeText(
            context,
            "Note : ${note.note}",
            Toast.LENGTH_LONG
        ).show()

        navigateToNoteDetailsFragment(note)
    }


    private fun navigateToManageNoteFragment(){
        val bundle: Bundle = bundleOf("is_edit" to false)
        findNavController().navigate(
            R.id.action_homeFragment_to_manageNoteFragment,
            bundle
        )
    }

    private fun navigateToNoteDetailsFragment(note:Note){
        val bundle: Bundle = bundleOf("title" to note.title,
            "note" to note.note)

        findNavController().navigate(
            R.id.action_homeFragment_to_noteDetailsFragment,
            bundle
        )
    }

}