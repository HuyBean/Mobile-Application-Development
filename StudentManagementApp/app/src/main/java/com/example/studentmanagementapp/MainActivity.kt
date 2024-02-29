package com.example.studentmanagementapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: StudentAdapter
    private var students = ArrayList<Student>() // Khai báo Student từ StudentAdapter.kt

    companion object {
        private const val REQUEST_CODE_ADD_STUDENT = 456 // Code thêm sinh viên
        private const val REQUEST_CODE_EDIT_STUDENT = 789 // Code sửa sinh viên
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        // Load student từ file resource
        students = loadStudentDataFromRawResource(R.raw.my_student_file)

        // Hiển thị danh sách sinh viên bằng ListView
        adapter = StudentAdapter(this, R.layout.student_layout, students)
        listView.adapter = adapter

        // Thêm sinh viên -> Chuyển qua StudentInfoActivity
        val imageButton = findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener {
            val intent = Intent(this, StudentInfoActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_STUDENT)
        }

        // Người dùng chọn một sinh viên trong danh sách -> Chuyển qua SecondActivity
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedStudent = students[position]

            // Gửi thông tin sinh viên đến SecondActivity
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("fullname", selectedStudent.name)
                putExtra("dob", selectedStudent.birthday)
                putExtra("gender", selectedStudent.gender)
                putExtra("classroom", selectedStudent.classroom)
            }
            startActivityForResult(intent, REQUEST_CODE_EDIT_STUDENT)
        }
    }

    // Áp dụng bài Read resource file trong Example 1
    private fun loadStudentDataFromRawResource(resourceId: Int): ArrayList<Student> {
        val students = ArrayList<Student>()
        try {
            val inputStream = resources.openRawResource(resourceId)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                val studentInfo = line!!.split(",") // Ngăn cách bởi dấu phẩy
                if (studentInfo.size == 4) {
                    val name = studentInfo[0]
                    val classroom = studentInfo[1]
                    val dob = studentInfo[2]
                    val gender = studentInfo[3]
                    val drawableId = R.drawable.student // Đây là ảnh mặc định

                    // Thêm thông tin sinh viên vào danh sách
                    students.add(Student(drawableId, name, classroom, dob, gender))
                }
            }
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return students
    }
    private fun saveStudentDataToRawResource(students: ArrayList<Student>) {
        try {
            val outputStream = openFileOutput("my_student_file.txt", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(outputStream))

            for (student in students) {
                val studentInfo = "${student.name},${student.classroom},${student.birthday},${student.gender}"
                writer.write(studentInfo)
                writer.newLine()
            }

            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Cập nhật sinh viên mời được thêm vào danh sách
        if (requestCode == REQUEST_CODE_ADD_STUDENT && resultCode == Activity.RESULT_OK) {
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

            saveStudentDataToRawResource(students)
            adapter.notifyDataSetChanged() // Cập nhật ListView
        } else if (requestCode == REQUEST_CODE_EDIT_STUDENT && resultCode == Activity.RESULT_OK) {
            val action = data?.getStringExtra("action")
            val originalFullName = data?.getStringExtra("original_fullname")

            // Nếu người dùng xóa sinh viên
            if (action == "delete") {
                students.removeIf { it.name == originalFullName }
                saveStudentDataToRawResource(students)
                adapter.notifyDataSetChanged() // Cập nhật ListView
            } else {
                val editedFullName = data?.getStringExtra("edited_fullname")
                val dob = data?.getStringExtra("dob")
                val gender = data?.getStringExtra("gender")
                val classroom = data?.getStringExtra("classroom")
                val index = students.indexOfFirst { it.name == originalFullName }
                if (index != -1) {
                    // Cập nhật thông tin sinh viên
                    students[index].name = editedFullName.toString()
                    students[index].birthday = dob.toString()
                    students[index].gender = gender.toString()
                    students[index].classroom = classroom.toString()
                }
                saveStudentDataToRawResource(students)
                adapter.notifyDataSetChanged() // Cập nhật ListView
            }
        }
    }
}