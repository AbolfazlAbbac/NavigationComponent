package com.example.livedata.notes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.livedata.notes.TABLE_NAME

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewNote(notesData: NotesData)


    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllNotes(): LiveData<MutableList<NotesData>>
}