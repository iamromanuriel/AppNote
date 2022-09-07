package com.roman.appnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.roman.appnotes.adapter.NoteAdapter
import com.roman.appnotes.databinding.ActivityMainBinding
import com.roman.appnotes.ui.NoteActivity
import com.roman.appnotes.viewmodel.Noteviewmodel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewmodel : Noteviewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database = NoteDatabase.getDatabase(this)
        viewmodel = ViewModelProvider(this, ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application))
            .get(Noteviewmodel::class.java)

        val recycler = binding.recyclerhome

        binding.buttonAdd.setOnClickListener {

            startActivity(Intent(this, NoteActivity::class.java))
        }

        viewmodel.allNote.observe(this, Observer {listNote ->

            val manager = GridLayoutManager(this, 2)
            val adapter = NoteAdapter(listNote)
            recycler.hasFixedSize()
            recycler.layoutManager = manager
            recycler.adapter = adapter
        })
    }
}