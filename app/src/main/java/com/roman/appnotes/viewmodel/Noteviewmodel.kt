package com.roman.appnotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.roman.appnotes.data.database.NoteDatabase
import com.roman.appnotes.data.database.NoteRepositorie
import com.roman.appnotes.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Noteviewmodel(application : Application) : AndroidViewModel(application) {
    val allNote : LiveData<List<Note>>
    val repository : NoteRepositorie

    init{
        val dao = NoteDatabase.getDatabase(application).note()
        repository = NoteRepositorie(dao)
        allNote = repository.allNotes
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }


    fun updateNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

    fun addNote(note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insrte(note)
    }
}