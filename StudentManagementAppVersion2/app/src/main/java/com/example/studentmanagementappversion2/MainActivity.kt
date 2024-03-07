package com.example.studentmanagementappversion2

import Student
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File

class MainActivity : AppCompatActivity(), StudentAdapter.OnItemClickListener {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var searchAutoCompleteTextView: AutoCompleteTextView
    private lateinit var buttonSearch: Button
    private lateinit var switchLayoutButton: Switch
    private val students = mutableListOf<Student>()
    private var isGridLayout = false

    companion object {
        private const val REQUEST_CODE_ADD_STUDENT = 111
        private const val REQUEST_CODE_EDIT_STUDENT = 222
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        searchAutoCompleteTextView = findViewById(R.id.searchAutoCompleteTextView)
        buttonSearch = findViewById(R.id.layoutToggleButton)
        switchLayoutButton = findViewById(R.id.switch1)

        val filePath = File(filesDir, "student.json").absolutePath

        val student = Student("HuyBean", "21KTPM2", "07/11/2003", "Male")
        val json = student.toJson()
        val file = File(filePath)

        try {
            file.writeText(json)
            Log.d("WriteToFile", "Data has been written to JSON file successfully")
        } catch (e: Exception) {
            Log.e("WriteToFile", "Error writing data to JSON file: ${e.message}")
        }

        val jsonText = File(filePath).readText()
        val loadedStudent = Student.fromJson(jsonText)

        Log.d(
            "LoadedStudent",
            "Name: ${loadedStudent.name}, Class: ${loadedStudent.classroom}, DOB: ${loadedStudent.birthday}, Gender: ${loadedStudent.gender}"
        )

        students.add(loadedStudent)

        studentAdapter = StudentAdapter(students, this)
        recyclerView.adapter = studentAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val studentNames = students.map { it.name }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, studentNames)
        searchAutoCompleteTextView.setAdapter(adapter)

        addButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT)
        }

        switchLayoutButton.setOnClickListener {
            isGridLayout = !isGridLayout
            updateLayoutManager()
        }

        buttonSearch.setOnClickListener {
            val searchText = searchAutoCompleteTextView.text.toString().trim()
            if (searchText.isNotEmpty()) {
                val filteredStudents = students.filter { it.name.contains(searchText, ignoreCase = true) }
                studentAdapter.updateList(filteredStudents)
            } else {
                // Nếu ô tìm kiếm trống, hiển thị tất cả sinh viên
                studentAdapter.updateList(students)
            }
        }


    }

    private fun updateLayoutManager() {
        if (isGridLayout) {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == Activity.RESULT_OK) {
            val name = data?.getStringExtra("new_student_name")
            val classroom = data?.getStringExtra("new_student_classroom")
            val birthday = data?.getStringExtra("new_student_birthday")
            val gender = data?.getStringExtra("new_student_gender")

            if (name != null && classroom != null && birthday != null && gender != null) {
                val newStudent = Student(name, classroom, birthday, gender)
                students.add(newStudent)
                studentAdapter.notifyDataSetChanged()
            }
        } else if (requestCode == REQUEST_CODE_EDIT_STUDENT && resultCode == Activity.RESULT_OK) {
            val deleteStudentName = data?.getStringExtra("delete_student_name")
            val deleteStudentClassroom = data?.getStringExtra("delete_student_classroom")
            val deleteStudentBirthday = data?.getStringExtra("delete_student_birthday")
            val deleteStudentGender = data?.getStringExtra("delete_student_gender")

            if (deleteStudentName != null && deleteStudentClassroom != null && deleteStudentBirthday != null && deleteStudentGender != null) {
                val deleteStudent = Student(deleteStudentName, deleteStudentClassroom, deleteStudentBirthday, deleteStudentGender)
                if (students.contains(deleteStudent)) {
                    students.remove(deleteStudent)
                    studentAdapter.notifyDataSetChanged()
                }
            } else {
                val updatedStudentName = data?.getStringExtra("edit_student_name")
                val updatedStudentClassroom = data?.getStringExtra("edit_student_classroom")
                val updatedStudentBirthday = data?.getStringExtra("edit_student_birthday")
                val updatedStudentGender = data?.getStringExtra("edit_student_gender")

                if (updatedStudentName != null && updatedStudentClassroom != null && updatedStudentBirthday != null && updatedStudentGender != null) {
                    val updatedStudent = Student(updatedStudentName, updatedStudentClassroom, updatedStudentBirthday, updatedStudentGender)
                    val index = students.indexOfFirst { it.name == deleteStudentName }
                    if (index != -1) {
                        students[index] = updatedStudent
                        studentAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onItemClick(student: Student) {
        val intent = Intent(this, UpdateStudentActivity::class.java).apply {
            putExtra("student_name", student.name)
            putExtra("student_classroom", student.classroom)
            putExtra("student_birthday", student.birthday)
            putExtra( "student_gender", student.gender)
        }
        startActivityForResult(intent, REQUEST_CODE_EDIT_STUDENT)
    }

}
