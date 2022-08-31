package com.roman.appnotes.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.roman.appnotes.data.model.Note


@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getallnote() : LiveData<List<Note>>

    @Insert
    fun insertNote(vararg note : Note)

    @Update
    fun updatenote(note : Note)

    @Delete
    fun deletenote(note : Note)

}