package com.example.studentmanagementapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: StudentAdapter
    private var students = ArrayList<Student>()

    companion object {
        private const val REQUEST_CODE_ADD_STUDENT = 456
        private const val REQUEST_CODE_EDIT_STUDENT = 789
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        // Set up the ListView
        students.add(Student(R.drawable.student, "Quoc Huy", "21KTPM2", "07/11/2003", "Male"))
        students.add(Student(R.drawable.student, "Hoang Sang", "21KTPM2", "07/11/2003", "Male"))
        students.add(Student(R.drawable.student, "Dinh Chuong", "21KTPM2", "07/11/2003", "Male"))
        students.add(Student(R.drawable.student, "Hai Nam", "21KTPM3", "07/11/2003", "Male"))

        adapter = StudentAdapter(this, R.layout.student_layout, students)
        listView.adapter = adapter

        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener {
            val intent = Intent(this, StudentInfoActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT)
        }

        // Set up item click listener for ListView
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedStudent = students[position]

            // Send data of the selected student to SecondActivity for editing
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("fullname", selectedStudent.name)
                putExtra("dob", selectedStudent.birthday)
                putExtra("gender", selectedStudent.gender)
                putExtra("classroom", selectedStudent.classroom)
            }
            startActivityForResult(intent, REQUEST_CODE_EDIT_STUDENT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == Activity.RESULT_OK) {
            // Handle result from StudentInfoActivity (adding a new student)
            val fullName = data?.getStringExtra("fullname")
            val dob = data?.getStringExtra("dob")
            val gender = data?.getStringExtra("gender")
            val classroom = data?.getStringExtra("classroom")

            val newStudent = Student(
                R.drawable.student,
                fullName.toString(),
                classroom.toString(),
                dob.toString(),
                gender.toString()
            )
            students.add(newStudent)

            adapter.notifyDataSetChanged()
        } else if (requestCode == REQUEST_CODE_EDIT_STUDENT && resultCode == Activity.RESULT_OK) {
            // Handle result from SecondActivity (editing or deleting a student)
            val action = data?.getStringExtra("action")
            val originalFullName = data?.getStringExtra("original_fullname")

            if (action == "delete") {
                students.removeIf { it.name == originalFullName }
            } else {
                val editedFullName = data?.getStringExtra("edited_fullname")
                val dob = data?.getStringExtra("dob")
                val gender = data?.getStringExtra("gender")
                val classroom = data?.getStringExtra("classroom")

                val index = students.indexOfFirst { it.name == originalFullName }
                if (index != -1) {
                    val editedStudent =
                        Student(R.drawable.student, editedFullName.toString(), classroom.toString(), dob.toString(), gender.toString())
                    students[index] = editedStudent
                }
            }

            adapter.notifyDataSetChanged()
        }
    }
}
