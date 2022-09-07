package com.roman.appnotes.data.database

import androidx.lifecycle.LiveData
import com.roman.appnotes.data.model.Note

class NoteRepositorie (private val noteDao : NoteDao) {
    val allNotes : LiveData<List<Note>> = noteDao.getallnote()

    suspend fun insrte(note : Note){
        noteDao.insertNote(note)
    }

    suspend fun delete(note : Note){
        noteDao.deletenote(note)
    }

    suspend fun update(note : Note){
        noteDao.updatenote(note)
    }
}