package com.roman.appnotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note")
data class Note (
        val title : String,
        val notes : String,
        val date : String,
        val color : Int?,
        val pinned: Boolean = false,
        @PrimaryKey(autoGenerate = true)
        val id : Int = 0
        ) : Serializable