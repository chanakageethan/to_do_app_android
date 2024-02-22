package com.test.todoapp.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.todoapp.R
import com.test.todoapp.data.model.Note

class NotesAdapter(
    private val notesList: List<Note>,
    private val clickListener: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.notes_list_item,parent,false)
        return  MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val note = notesList[position]
        holder.bind(note,clickListener)

    }

    override fun getItemCount(): Int {
        return notesList.size
    }


    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(note:Note ,clickListener: (Note) -> Unit){
            val titleTextView = view.findViewById<TextView>(R.id.etTitle)
            titleTextView.text = note.title

            view.setOnClickListener(){
                clickListener(note)
            }
        }

    }

}