package com.example.studentmanagementapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
data class Student(
    val icon: Int,
    var name: String,
    var classroom: String,
    var birthday: String,
    var gender: String
)
class StudentAdapter(context: Context, resource: Int, objects: List<Student>) :
    ArrayAdapter<Student>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.student_layout, parent, false)
        }

        val student = getItem(position)

        val iconImageView = view?.findViewById<ImageView>(R.id.iconIV)
        val nameTextView = view?.findViewById<TextView>(R.id.nameTV)
        val classroomTextView = view?.findViewById<TextView>(R.id.classroomTV)
        val birthdayTextView = view?.findViewById<TextView>(R.id.birthdayTV)
        val genderTextView = view?.findViewById<TextView>(R.id.genderTV)

        iconImageView?.setImageResource(student?.icon ?: R.drawable.student)
        nameTextView?.text = student?.name
        classroomTextView?.text = student?.classroom
        birthdayTextView?.text = student?.birthday
        genderTextView?.text = student?.gender

        return view!!
    }
}
