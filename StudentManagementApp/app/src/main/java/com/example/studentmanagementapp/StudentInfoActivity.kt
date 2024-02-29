package com.example.studentmanagementapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class StudentInfoActivity : AppCompatActivity() {
    private lateinit var fullnameEditText: EditText
    private lateinit var dobEditText: EditText
    private lateinit var editTextClass: EditText
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var otherGenderRadioButton: RadioButton

    companion object {
        private const val REQUEST_CODE_SELECT_CLASS = 123
        // Mã yêu cầu để phân biệt giữa các kết quả
    }

    private var selectedClassroom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)

        fullnameEditText = findViewById(R.id.fullnameTextView)
        dobEditText = findViewById(R.id.dobTextView)
        editTextClass = findViewById(R.id.editTextText)
        maleRadioButton = findViewById(R.id.male)
        femaleRadioButton = findViewById(R.id.female)
        otherGenderRadioButton = findViewById(R.id.otherGender)

        editTextClass.setOnClickListener {
            val intentClass = Intent(this, ClassActivity::class.java)
            startActivityForResult(intentClass, REQUEST_CODE_SELECT_CLASS)
        }

        // Xử lý nút Save và trở về MainActivity
        val saveButton = findViewById<Button>(R.id.saveButton1)
        saveButton.setOnClickListener {
            val fullName = fullnameEditText.text.toString()
            val dob = dobEditText.text.toString()
            val gender = when {
                maleRadioButton.isChecked -> "Male"
                femaleRadioButton.isChecked -> "Female"
                else -> "Other"
            }

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("fullname", fullName)
                putExtra("dob", dob)
                putExtra("gender", gender)
                // Thêm giá trị lớp học được chọn vào intent nếu có
                selectedClassroom?.let { putExtra("classroom", it) }
            }
            setResult(Activity.RESULT_OK, intent)
            finish() // Kết thúc StudentInfoActivity và trở về MainActivity
        }
    }

    // Xử lý kết quả trả về từ ClassActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_CLASS && resultCode == Activity.RESULT_OK) {
            // Nhận giá trị lớp học được chọn từ Intent
            selectedClassroom = data?.getStringExtra("classroom")
            // Hiển thị giá trị lớp học trong editTextClass
            editTextClass.setText(selectedClassroom ?: "Choose Class")
        }
    }
}
