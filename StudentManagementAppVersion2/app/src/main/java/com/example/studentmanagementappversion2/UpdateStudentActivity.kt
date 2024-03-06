package com.example.studentmanagementappversion2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView

class UpdateStudentActivity : AppCompatActivity() {
    private lateinit var fullNameTV: EditText
    private lateinit var dobTV: EditText
    private lateinit var spinner: Spinner
    private lateinit var maleButton: RadioButton
    private lateinit var femaleButton: RadioButton
    private lateinit var otherButton: RadioButton
    private lateinit var deleteButton: Button
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)

        fullNameTV = findViewById(R.id.fullnameTextView)
        dobTV = findViewById(R.id.dobTextView)
        spinner = findViewById(R.id.classSpinner)
        maleButton = findViewById(R.id.male)
        femaleButton = findViewById(R.id.female)
        otherButton = findViewById(R.id.otherGender)
        deleteButton = findViewById(R.id.deleteButton1)
        saveButton = findViewById(R.id.saveButtonSecondActivity)

        // Nhận dữ liệu từ Intent
        val studentName = intent.getStringExtra("student_name")
        val studentClassroom = intent.getStringExtra("student_classroom")
        val studentBirthday = intent.getStringExtra("student_birthday")
        val studentGender = intent.getStringExtra("student_gender")

        // Hiển thị dữ liệu trong giao diện người dùng
        fullNameTV.setText(studentName)
        dobTV.setText(studentBirthday)

        // Thiết lập dữ liệu cho Spinner
        val spinnerArray = arrayOf("21KTPM1", "21KTPM2", "21KTPM3") // Thay bằng danh sách lớp học thực tế của bạn
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Tìm vị trí của giá trị studentClassroom trong mảng spinnerArray
        val position = spinnerArray.indexOf(studentClassroom)
        // Nếu tìm thấy, chọn giá trị tương ứng trong Spinner
        if (position != -1) {
            spinner.setSelection(position)
        }

        when (studentGender) {
            "Male" -> maleButton.isChecked = true
            "Female" -> femaleButton.isChecked = true
            "Other" -> otherButton.isChecked = true
        }

        // Xử lý khi nhấn nút Save
        saveButton.setOnClickListener {
            // Trả về kết quả với dữ liệu đã cập nhật
            val resultIntent = Intent().apply {
                putExtra("edit_student_name", fullNameTV.text.toString())
                putExtra("edit_student_classroom", spinner.selectedItem.toString())
                putExtra("edit_student_birthday", dobTV.text.toString())
                putExtra("edit_student_gender", when {
                    maleButton.isChecked -> "Male"
                    femaleButton.isChecked -> "Female"
                    else -> "Other"
                })
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Kết thúc UpdateStudentActivity
        }

        // Xử lý khi nhấn nút Delete
        deleteButton.setOnClickListener {
            // Gửi dữ liệu để xóa
            val resultIntent = Intent().apply {
                putExtra("delete_student_name", studentName)
                putExtra("delete_student_classroom", studentClassroom)
                putExtra("delete_student_birthday", studentBirthday)
                putExtra("delete_student_gender", studentGender)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Kết thúc UpdateStudentActivity
        }
    }
}
