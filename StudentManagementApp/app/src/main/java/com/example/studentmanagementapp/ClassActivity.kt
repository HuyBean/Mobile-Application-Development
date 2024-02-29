package com.example.studentmanagementapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.io.BufferedReader
import java.io.InputStreamReader

class ClassActivity : AppCompatActivity() {

    private lateinit var saveButton: Button
    private lateinit var selectedClassroom: String
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        saveButton = findViewById(R.id.saveButton2)
        listView = findViewById(R.id.list_view_class)

        // Đọc danh sách lớp học từ file resource
        val classrooms = readClassroomsFromRawResource(R.raw.my_class_file)

        val adapter = ClassAdapter(this, R.layout.class_layout, classrooms)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            selectedClassroom = classrooms[position].classroom
        }

        saveButton.setOnClickListener {
            val intent = Intent().apply {
                putExtra("classroom", selectedClassroom)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun readClassroomsFromRawResource(resourceId: Int): ArrayList<Class> {
        val classrooms = ArrayList<Class>()
        try {
            val inputStream = resources.openRawResource(resourceId)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                // Ngăn cách các thông tin bởi dấu phẩy
                val classNames = line?.split(",") ?: emptyList()
                for (className in classNames) {
                    classrooms.add(Class(R.drawable.study, className.trim()))
                }
            }
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return classrooms
    }
}
