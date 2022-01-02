package com.allen.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class DB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama_awal") val first: String?,
    @ColumnInfo(name = "nama_akhir") val lasts: String?
)