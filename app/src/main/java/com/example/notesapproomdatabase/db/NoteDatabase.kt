package com.example.notesapproomdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapproomdatabase.model.Note

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNotesDau() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            if (INSTANCE ==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "contactDB").build()
                }

            }
            return INSTANCE!!
        }
    }
}