package com.test.todoapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.todoapp.data.local_data.Note
import com.test.todoapp.repository.NotesRepository
import com.test.todoapp.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {
    val notes = repository.notes

    private var isUpdateOrDelete = false
    private lateinit var notesToUpdateOrDelete: Note

    val inputTitle = MutableLiveData<String>()
    val inputNoteContent = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {

        if (inputTitle.value == null) {
            statusMessage.value = Event("Please enter the Title")
        } else if (inputNoteContent.value == null) {
            statusMessage.value = Event("Please enter the Note")
        } else {
            if (isUpdateOrDelete) {
                notesToUpdateOrDelete.noteTitle = inputTitle.value!!
                notesToUpdateOrDelete.noteContent = inputNoteContent.value!!
                update(notesToUpdateOrDelete)
            } else {
                val title = inputTitle.value!!
                val note = inputNoteContent.value!!
                insert(Note(0, title, note))
            }
        }


        val title = inputTitle.value!!
        val note = inputNoteContent.value!!

        insert(Note(0, title, note))
        inputTitle.value = ""
        inputNoteContent.value = ""
    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(notesToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        val newRowId = repository.insert(note)
        withContext(Dispatchers.Main) {
            if (newRowId > -1) {
                statusMessage.value = Event("Subscriber Inserted $newRowId")

            } else {
                statusMessage.value = Event(" Error Occurred!")

            }
        }
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRows = repository.update(note)

        withContext(Dispatchers.Main) {
            if (numberOfRows > 0) {
                statusMessage.value = Event("Note Updated successfully $numberOfRows")
            } else {
                statusMessage.value = Event("Error Occurred! $numberOfRows")
            }

            inputTitle.value = ""
            inputNoteContent.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.delete(note)
        withContext(Dispatchers.Main) {
            if (numberOfRowsDeleted > 0) {
                inputTitle.value = ""
                inputNoteContent.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$numberOfRowsDeleted Rows Deleted Successfully")
            } else {
                statusMessage.value = Event("Error Occurred")
            }
        }
    }

    fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        val numberOfRowsDeleted = repository.deleteAll()

        withContext(Dispatchers.Main) {
            if (numberOfRowsDeleted > 0) {
                statusMessage.value = Event("$numberOfRowsDeleted  Deleted Successfully")
            } else {
                statusMessage.value = Event("Error Occurred")

            }
        }
    }

    fun initUpdateAndDelete(note: Note){

        inputTitle.value = note.noteTitle
        inputNoteContent.value = note.noteContent
        isUpdateOrDelete = true
        notesToUpdateOrDelete = note

        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


}