package  com.example.studentmanagementappversion2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanagementappversion2.R
import com.example.studentmanagementappversion2.data.Student

class StudentAdapter(private var students: List<Student>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(student: Student)
    }

    // Inner class ViewHolder được giữ nguyên

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTV)
        val classroomTextView: TextView = itemView.findViewById(R.id.classroomTV)
        val birthdayTextView: TextView = itemView.findViewById(R.id.birthdayTV)
        val genderTextView: TextView = itemView.findViewById(R.id.genderTV)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val student = students[position]
                listener.onItemClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val studentView = inflater.inflate(R.layout.student_item, parent, false)
        return ViewHolder(studentView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]

        holder.nameTextView.text = student.name
        holder.classroomTextView.text = student.classroom
        holder.birthdayTextView.text = student.birthday
        holder.genderTextView.text = student.gender
    }

    fun submitList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return students.size
    }
}
