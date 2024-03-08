package com.example.dagger_hilt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import com.example.TABLE_NAME

@Dao
interface Dao {

    @Insert
    fun saveNewNote(noteModel: NoteModel)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY text")
    fun getAllNotes(): MutableList<NoteModel>

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteNotes()
}