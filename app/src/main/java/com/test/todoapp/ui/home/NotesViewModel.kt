package com.test.todoapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.todoapp.data.local_data.Note
import com.test.todoapp.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private  val repository: NotesRepository):ViewModel() {
    val notes = repository.notes

    val inputTitle  = MutableLiveData<String>()
    val inputNoteContent = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
        val title =  inputTitle.value!!
        val note = inputNoteContent.value!!

        insert(Note(0,title,note))
        inputTitle.value = ""
        inputNoteContent.value = ""
    }

    fun clearAllOrDelete(){
        clearAll()
    }

    fun insert(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun update(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun delete(note: Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun clearAll()=viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }




}