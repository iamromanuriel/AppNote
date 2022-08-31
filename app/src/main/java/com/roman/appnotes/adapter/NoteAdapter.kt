package com.roman.appnotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.appnotes.R
import com.roman.appnotes.data.model.Note

class NoteAdapter (val listNote : List<Note>) : RecyclerView.Adapter<ViewHolderNote>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNote {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolderNote(layoutInflater.inflate(R.layout.card_note, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderNote, position: Int) {
        val item = listNote[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = listNote.size

}