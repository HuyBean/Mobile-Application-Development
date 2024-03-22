package com.example.studentmanagementappversion2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementappversion2.adapter.StudentAdapter
import com.example.studentmanagementappversion2.data.Student
import com.example.studentmanagementappversion2.data.StudentDAO
import com.example.studentmanagementappversion2.data.StudentDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), StudentAdapter.OnItemClickListener {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var searchAutoCompleteTextView: AutoCompleteTextView
    private lateinit var buttonSearch: Button
    private lateinit var switchLayoutButton: Switch
    private var isGridLayout = false

    private lateinit var studentDao: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)
        searchAutoCompleteTextView = findViewById(R.id.searchAutoCompleteTextView)
        buttonSearch = findViewById(R.id.layoutToggleButton)
        switchLayoutButton = findViewById(R.id.switch1)

        val database = StudentDatabase.getInstance(this)
        studentDao = database.studentDao()

        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(ArrayList(), this)
        recyclerView.adapter = studentAdapter

        addButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT)
        }

        switchLayoutButton.setOnCheckedChangeListener { _, isChecked ->
            isGridLayout = isChecked
            updateLayoutManager()
        }

        buttonSearch.setOnClickListener {
            val searchText = searchAutoCompleteTextView.text.toString().trim()
            if (searchText.isNotEmpty()) {
                val filteredStudents = searchStudents(searchText)
                studentAdapter.submitList(filteredStudents)
            } else {
                loadStudents()
            }
        }

        val studentNames = loadStudentNames()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, studentNames)
        searchAutoCompleteTextView.setAdapter(adapter)

        loadStudents()
    }

    private fun loadStudents() {
        val students = studentDao.getAllStudents()
        // Gửi danh sách đã lọc đến adapter
        studentAdapter.submitList(students)

    }


    private fun loadStudentNames(): List<String> {
        val students = studentDao.getAllStudents().toMutableList()
        return students.map { it.name }
    }


    private fun searchStudents(query: String): List<Student> {
        return studentDao.searchStudents("%$query%")
    }

    private fun updateLayoutManager() {
        recyclerView.layoutManager = if (isGridLayout) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
    }

    override fun onItemClick(student: Student) {
        val intent = Intent(this, UpdateStudentActivity::class.java).apply {
            putExtra("student_id", student.id) // Thêm ID vào intent
            putExtra("student_name", student.name)
            putExtra("student_classroom", student.classroom)
            putExtra("student_birthday", student.birthday)
            putExtra("student_gender", student.gender)
        }
        startActivityForResult(intent, REQUEST_CODE_EDIT_STUDENT)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == RESULT_OK) {
            val name = data?.getStringExtra("new_student_name").toString()
            val classroom = data?.getStringExtra("new_student_classroom").toString()
            val birthday = data?.getStringExtra("new_student_birthday").toString()
            val gender = data?.getStringExtra("new_student_gender").toString()

            if (name != null && classroom != null && birthday != null && gender != null) {
                val newStudent = Student(
                    name = name,
                    classroom = classroom,
                    birthday = birthday,
                    gender = gender
                )
                studentDao.insertStudent(newStudent)
                loadStudents()
            }
        }
        if(requestCode == REQUEST_CODE_EDIT_STUDENT && resultCode == RESULT_OK)
        {
            if(resultCode == Activity.RESULT_OK) {
                val id = data?.getIntExtra("student_id", -1) // Nhận ID từ intent
                val name = data?.getStringExtra("edit_student_name").toString()
                val classroom = data?.getStringExtra("edit_student_classroom").toString()
                val birthday = data?.getStringExtra("edit_student_birthday").toString()
                val gender = data?.getStringExtra("edit_student_gender").toString()

                if (name != null && classroom != null && birthday != null && gender != null) {
                    val updateStudent = Student(
                        id = id!!,
                        name = name,
                        classroom = classroom,
                        birthday = birthday,
                        gender = gender
                    )
                    studentDao.updateStudent(updateStudent)
                    loadStudents()
                }
            }
            if(data?.hasExtra("delete_student_name") == true) {
                val id = data?.getIntExtra("student_id", -1) // Nhận ID từ intent
                val name = data?.getStringExtra("delete_student_name").toString()
                val classroom = data?.getStringExtra("delete_student_classroom").toString()
                val birthday = data?.getStringExtra("delete_student_birthday").toString()
                val gender = data?.getStringExtra("delete_student_gender").toString()
                Log.d("MainActivity", "Delete student: $name")
                if (name != null && classroom != null && birthday != null && gender != null) {
                    val deleteStudent = Student(
                        id = id!!,
                        name = name,
                        classroom = classroom,
                        birthday = birthday,
                        gender = gender
                    )
                    studentDao.deleteStudent(deleteStudent)
                    Log.i("MainActivity", "StudentDAO: $studentDao")
                    loadStudents()
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_ADD_STUDENT = 111
        private const val REQUEST_CODE_EDIT_STUDENT = 222
    }
}
