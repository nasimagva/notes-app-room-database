package com.example.notesapproomdatabase.util

import androidx.lifecycle.LiveData
import com.example.notesapproomdatabase.db.NoteDao
import com.example.notesapproomdatabase.model.Note

class NoteRepository(
    private val noteDao: NoteDao
) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun update(note: Note){
        noteDao.update(note)
    }
}