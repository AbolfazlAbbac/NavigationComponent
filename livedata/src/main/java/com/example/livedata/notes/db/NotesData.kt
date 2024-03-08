package com.example.livedata.notes.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.livedata.notes.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NotesData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val desc: String = ""

)
