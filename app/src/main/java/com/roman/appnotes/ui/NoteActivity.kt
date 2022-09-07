package com.roman.appnotes.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.roman.appnotes.R
import com.roman.appnotes.data.database.NoteDatabase
import com.roman.appnotes.data.model.Note
import com.roman.appnotes.databinding.ActivityNoteBinding
import com.roman.appnotes.utils.Utilsfunction
import com.roman.appnotes.viewmodel.Noteviewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNoteBinding
    private lateinit var viewmodel : Noteviewmodel
    private lateinit var intentnote : Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intentnote = intent.extras?.get("NOTE") as Note

        viewmodel = ViewModelProvider(this, ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application))
            .get(Noteviewmodel::class.java)

        binding.editTitle.setText(intentnote.title)
        binding.editNote.setText(intentnote.notes)


        binding.ImageviewNotes.setOnClickListener {
            try{
                val note =Note(binding.editTitle.text.toString(),binding.editNote.text.toString(),getDate(),getnumcolor())
                Toast.makeText(this, note.toString(),Toast.LENGTH_SHORT).show()
            }catch(e : Exception){
                println(e.message.toString())
            }

            /*val title = binding.editTitle.text.toString()
            val note = binding.editNote.text.toString()

            val notenew = Note(title, note,getDate(), getnumcolor(), false)
            try{
                viewmodel.addNote(notenew)
                finish()
            }catch (e : Exception){
                println(e.message.toString())
            }

             */

        }

        binding.ImageviewDelete.setOnClickListener {

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