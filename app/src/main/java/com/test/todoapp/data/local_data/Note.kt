package com.test.todoapp.data.local_data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_data_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val noteId: Int,

    @ColumnInfo(name = "note_title")
    val noteTitle: String,

    @ColumnInfo(name = "note_content")
    val noteContent: String

)