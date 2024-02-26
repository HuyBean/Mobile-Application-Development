package com.example.studentmanagementapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
data class Class(
    val icon: Int,
    val classroom: String,
)
class ClassAdapter(context: Context, resource: Int, objects: List<Class>) :
    ArrayAdapter<Class>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.class_layout, parent, false)
        }

        val student = getItem(position)

        val iconImageView = view?.findViewById<ImageView>(R.id.imageViewClassLayout)
        val classroomTextView = view?.findViewById<TextView>(R.id.textViewClassLayout)

        iconImageView?.setImageResource(student?.icon ?: R.drawable.student)
        classroomTextView?.text = student?.classroom

        return view!!
    }
}
