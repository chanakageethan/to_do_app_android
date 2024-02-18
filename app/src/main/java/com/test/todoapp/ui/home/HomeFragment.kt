package com.test.todoapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.test.todoapp.R
import com.test.todoapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment(){

    private lateinit var binding :FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)


        binding.addNote.setOnClickListener {
            navigateToManageNoteFragment()
        }

        return  binding.root
    }





    private fun navigateToManageNoteFragment(){
        val bundle: Bundle = bundleOf("is_edit" to false)
        findNavController().navigate(
            R.id.action_homeFragment_to_manageNoteFragment,
            bundle
        )
    }

}