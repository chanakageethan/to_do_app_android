package com.test.todoapp.data.local_data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDAO {

    @Insert
    suspend fun insertNote(note:Note)

    @Update
    suspend fun updateNote(note:Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("DELETE FROM notes_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM notes_data_table")
    fun getAllNotes(): LiveData<List<Note>>


}