package com.roman.appnotes.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.roman.appnotes.R
import com.roman.appnotes.data.model.Note
import com.roman.appnotes.databinding.CardNoteBinding
import com.roman.appnotes.ui.NoteActivity


class ViewHolderNote (view : View) : RecyclerView.ViewHolder(view) {
    val bindingCard = CardNoteBinding.bind(view)


    @SuppressLint("ResourceAsColor")
    fun render (note : Note){
        bindingCard.textviewtitle.text = note.title
        bindingCard.textNotes.text = note.notes
        bindingCard.textDate.text = note.date
        bindingCard.notesContainer.setCardBackgroundColor(Color.GRAY)
        if(note.pinned.equals(false)){
            bindingCard.imagePin.visibility = View.INVISIBLE
        }

        bindingCard.textviewtitle.setOnClickListener {
            val intent = Intent(bindingCard.textviewtitle.context, NoteActivity::class.java)
            intent.putExtra("NOTE", note)
            bindingCard.textviewtitle.context.startActivity(intent)
        }


    }


}