package com.roman.appnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.appnotes.adapter.NoteAdapter
import com.roman.appnotes.data.database.NoteDatabase
import com.roman.appnotes.databinding.ActivityMainBinding
import com.roman.appnotes.ui.NoteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = NoteDatabase.getDatabase(this)

        var recycler = binding.recyclerhome

        binding.buttonAdd.setOnClickListener {

            startActivity(Intent(this, NoteActivity::class.java))
        }

        database.note().getallnote().observe(this, Observer { listNote ->

            println(listNote.toString())
            var manager = GridLayoutManager(this, 2)
            var adapter = NoteAdapter(listNote)
            recycler.hasFixedSize()
            recycler.layoutManager = manager
            recycler.adapter = adapter
        })
    }
}