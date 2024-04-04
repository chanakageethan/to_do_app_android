package com.test.todoapp.repository

import com.test.todoapp.data.local_data.Note
import com.test.todoapp.data.local_data.NotesDAO

class NotesRepository(private val dao:NotesDAO) {
    val notes = dao.getAllNotes()

    suspend fun insert(note: Note):Long{
        return dao.insertNote(note)
    }

    suspend fun update(note: Note) :Int{
        return dao.updateNote(note)
    }

    suspend fun delete(note: Note):Int{
        return dao.delete(note)
    }

    suspend fun deleteAll():Int{
        return dao.deleteAll()
    }



}