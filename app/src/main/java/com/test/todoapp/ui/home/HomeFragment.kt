package com.test.todoapp.ui.home

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


class HomeFragment : Fragment(){

    private lateinit var binding :FragmentHomeBinding
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)



        val dao = NotesDatabase.getInstance(requireContext()).notesDAO
        val repository =  NotesRepository(dao)
        val factory =  NotesViewModelFactory(repository)
        notesViewModel = ViewModelProvider(this, factory!!)[NotesViewModel::class.java]
        binding.myViewModel = notesViewModel
        binding.lifecycleOwner = this

        initRecycleView()
        displaySubscribersList()


        binding.addNote.setOnClickListener {
            navigateToManageNoteFragment()
        }

        return  binding.root
    }

    private  fun initRecycleView(){


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NotesAdapter(
            { selectedItem: com.test.todoapp.data.local_data.Note ->
                listItemClicked(selectedItem)
            })
        displaySubscribersList()
        binding.recyclerView.adapter =adapter
    }





    private fun displaySubscribersList(){
        notesViewModel.notes.observe(viewLifecycleOwner, Observer {
            Log.i("MYTAG", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }


    private fun listItemClicked(note: com.test.todoapp.data.local_data.Note){
        Toast.makeText(
            context,
            "Note : ${note.noteTitle}",
            Toast.LENGTH_LONG
        ).show()

//        navigateToNoteDetailsFragment(note)
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