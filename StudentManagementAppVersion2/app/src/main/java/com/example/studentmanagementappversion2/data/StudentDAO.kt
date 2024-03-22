package com.example.studentmanagementappversion2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface StudentDAO {
    @Query("SELECT * FROM students ORDER BY id")
    fun readAllData(): LiveData<List<Student>>

    @Query("SELECT * FROM students")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM students WHERE name LIKE :query")
    fun searchStudents(query: String): List<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)
}
