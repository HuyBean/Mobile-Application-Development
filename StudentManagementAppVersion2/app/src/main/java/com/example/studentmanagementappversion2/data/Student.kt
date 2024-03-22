package com.example.studentmanagementappversion2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "classroom")
    val classroom: String,
    @ColumnInfo(name = "birthday")
    val birthday: String,
    @ColumnInfo(name = "gender")
    val gender: String,
)

