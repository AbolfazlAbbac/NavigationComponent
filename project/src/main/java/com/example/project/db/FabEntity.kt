package com.example.project.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.project.utils.Utils

@Entity(Utils.FAB_TABLE)
data class FabEntity(
    @PrimaryKey(autoGenerate = false) var id: Int = 0,
    var name: String = "",
    var year: String = "",
    var conutry: String = "",
    var poster: String = "",
    var rate: String = ""
)