package com.example.studentmanagementappversion2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDAO

    companion object {
        private const val DB_NAME = "student_db"
        @Volatile
        private var instance: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it}
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, StudentDatabase::class.java, DB_NAME).allowMainThreadQueries().build()
    }
}
