package com.example.notesapproomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapproomdatabase.adapter.NoteRVAdapter
import com.example.notesapproomdatabase.model.Note
import com.example.notesapproomdatabase.util.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteRVAdapter.NoteClickInterface {
    lateinit var noteRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRV = findViewById(R.id.idRVNotes)
        addFAB = findViewById(R.id.idFABAddNote)

        noteRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this, this, this)
        noteRV.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            val intent= Intent(this, AddEditNote::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent= Intent(this, AddEditNote::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()
    }
}