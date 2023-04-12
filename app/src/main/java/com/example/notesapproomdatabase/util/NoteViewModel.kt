package com.example.notesapproomdatabase.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapproomdatabase.db.NoteDatabase
import com.example.notesapproomdatabase.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDau()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}