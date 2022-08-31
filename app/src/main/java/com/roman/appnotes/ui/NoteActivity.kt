package com.roman.appnotes.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.roman.appnotes.R
import com.roman.appnotes.data.database.NoteDatabase
import com.roman.appnotes.data.model.Note
import com.roman.appnotes.databinding.ActivityNoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNoteBinding
    lateinit var noteintent : Note
    private var intentstate = false

    private lateinit var database : NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = NoteDatabase.getDatabase(this)

        try{
            supportActionBar?.title = "Edit"
            noteintent = intent.extras?.get("NOTE") as Note
            binding.editTitle.setText(noteintent.title)
            binding.editNote.setText(noteintent.notes)
            intentstate = true
        }catch(e : Exception){
            supportActionBar?.title = "New note"
            Log.e("Error intent get",e.message.toString())
        }



        binding.ImageviewNotes.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val note = binding.editNote.text.toString()

            if(intentstate){
                var noteupdate = Note(title, note, getDate(), noteintent.color, noteintent.pinned)
                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        database.note().updatenote(noteupdate)
                    }catch(e : Exception){
                        Log.e("Error update note",e.message.toString())
                    }
                }

            }else{
                val notenew = Note(title,note,getDate(),getnumcolor(),false)

                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        database.note().insertNote(notenew)
                        println("Si kemoo khee")

                    }catch(e : Exception){
                        println("Erro es : ${e.message}")
                    }
                }
            }
        }
    }

    fun getnumcolor() : Int{
        var colors = listOf<Int>(
            R.color.note_blue,
            R.color.note_green,
            R.color.note_red,
            R.color.note_yellow
        )
        var random = Random()
        var randomColor = random.nextInt(colors.size)

        return colors.get(randomColor)
    }

    fun getDate() : String{
        val sdg = SimpleDateFormat("dd/MM/yyyy")
        return sdg.format(Date()).toString()
    }
}