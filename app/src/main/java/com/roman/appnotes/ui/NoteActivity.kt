package com.roman.appnotes.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private var intentnote: Note ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewmodel = ViewModelProvider(this, ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application))
            .get(Noteviewmodel::class.java)

        try{
            intentnote = intent.extras?.get("NOTE") as Note
        }catch(e: Exception){
            intentnote = null
        }

        if(intentnote != null){
            binding.editTitle.setText(intentnote!!.title)
            binding.editNote.setText(intentnote!!.notes)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.pin ->{

                val title = binding.editTitle.text.toString()
                val note = binding.editNote.text.toString()
                if(intentnote != null){
                    val noteupdate = Note(title, note,getDate(), intentnote?.color, intentnote!!.pinned,intentnote!!.id)
                    try{
                        viewmodel.updateNote(noteupdate)
                    }catch (e : Exception){
                        println(e.message.toString())
                    }
                }else{
                    val notenew = Note(title, note, getDate(),getnumcolor(),false)
                    try{
                        viewmodel.addNote(notenew)
                    }catch (e: Exception){
                        println(e.message)
                    }
                }
                finish()
                true
            }
            R.id.delete ->{
                try{
                    if(intentnote != null){
                        showAlertDialog(intentnote)
                    }else{
                        Toast.makeText(this, "No puedes borrar",Toast.LENGTH_SHORT).show()
                    }


                }catch(e: Exception){
                    println(e.message)
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun showAlertDialog(note: Note?){
        AlertDialog.Builder(this)
            .setTitle("Advertencia")
            .setMessage("¿Quires eliminar esta nota?")
            .setPositiveButton(android.R.string.ok,
            DialogInterface.OnClickListener { dialogInterface, i ->
                viewmodel.delete(note!!)
                finish()})
            .setNegativeButton(android.R.string.cancel,
            DialogInterface.OnClickListener { dialogInterface, i ->  })
            .show()
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