package com.example.studentmanagementapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class ClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class)

        val listView = findViewById<ListView>(R.id.list_view_class)

        val classrooms = ArrayList<Class>()
        classrooms.add(Class(R.drawable.study, "21KTPM1"))
        classrooms.add(Class(R.drawable.study, "21KTPM2"))
        classrooms.add(Class(R.drawable.study, "21KTPM3"))

        val adapter = ClassAdapter(this, R.layout.class_layout, classrooms)
        listView.adapter = adapter
    }
}
