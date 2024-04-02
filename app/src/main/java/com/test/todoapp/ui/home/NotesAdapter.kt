package com.test.todoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.todoapp.R
import com.test.todoapp.data.model.Note
import com.test.todoapp.databinding.NotesListItemBinding

class NotesAdapter(
    private val clickListener: (com.test.todoapp.data.local_data.Note) -> Unit
) : RecyclerView.Adapter<MyViewHolder>(){

    private val notesList = ArrayList<com.test.todoapp.data.local_data.Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        val listItem = layoutInflater.inflate(R.layout.notes_list_item,parent,false)
        val  binding:NotesListItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.notes_list_item,parent,false)
        return  MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(notesList[position],clickListener)

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setList(notes: List<com.test.todoapp.data.local_data.Note>){
        notesList.clear()
        notesList.addAll(notes)
    }




}

class MyViewHolder(val binding: NotesListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note: com.test.todoapp.data.local_data.Note, clickListener: (com.test.todoapp.data.local_data.Note) -> Unit){
//            val titleTextView = view.findViewById<TextView>(R.id.tvTitle)
//            titleTextView.text = note.title

        binding.tvTitle.text = note.noteTitle
        binding.tvNote.text = note.noteContent



        binding.listItemLayout.setOnClickListener(){
            clickListener(note)
        }
    }

}