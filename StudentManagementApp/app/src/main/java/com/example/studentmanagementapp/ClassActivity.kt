package com.example.studentmanagementapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ClassActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var selectedClassroom: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        saveButton = findViewById(R.id.saveButton2)
        val listView = findViewById<ListView>(R.id.list_view_class)

        val classrooms = ArrayList<Class>()
        classrooms.add(Class(R.drawable.study, "20KTPM1"))
        classrooms.add(Class(R.drawable.study, "20KTPM2"))
        classrooms.add(Class(R.drawable.study, "20KTPM3"))
        classrooms.add(Class(R.drawable.study, "21KTPM1"))
        classrooms.add(Class(R.drawable.study, "21KTPM2"))
        classrooms.add(Class(R.drawable.study, "21KTPM3"))
        classrooms.add(Class(R.drawable.study, "22KTPM1"))
        classrooms.add(Class(R.drawable.study, "22KTPM2"))
        classrooms.add(Class(R.drawable.study, "22KTPM3"))
        classrooms.add(Class(R.drawable.study, "23KTPM1"))
        classrooms.add(Class(R.drawable.study, "23KTPM2"))
        classrooms.add(Class(R.drawable.study, "23KTPM3"))

        val adapter = ClassAdapter(this, R.layout.class_layout, classrooms)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            selectedClassroom = classrooms[position].classroom
        }

        // Xử lý sự kiện khi nút save được nhấn
        saveButton.setOnClickListener {
            // Tạo intent để trở về StudentInfoActivity và gửi thông tin lớp học được chọn
            val intent = Intent().apply {
                putExtra("classroom", selectedClassroom)
            }
            setResult(Activity.RESULT_OK, intent)
            finish() // Kết thúc Activity và quay lại StudentInfoActivity
        }
    }
}
