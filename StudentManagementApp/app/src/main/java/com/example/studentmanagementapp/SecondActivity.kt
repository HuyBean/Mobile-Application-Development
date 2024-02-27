package com.example.studentmanagementapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class SecondActivity : AppCompatActivity() {
    private lateinit var fullnameEditText: EditText
    private lateinit var dobEditText: EditText
    private lateinit var textViewClassroom: TextView
    private lateinit var imageViewClass: ImageView
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var otherGenderRadioButton: RadioButton
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button

    private var selectedClassroom: String? = null

    companion object {
        private const val REQUEST_CODE_SELECT_CLASS = 888
        private const val REQUEST_CODE_EDIT_STUDENT = 789
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        fullnameEditText = findViewById(R.id.fullnameTextView)
        dobEditText = findViewById(R.id.dobTextView)
        textViewClassroom = findViewById(R.id.textViewClassroom)
        imageViewClass = findViewById(R.id.imageViewClass)
        maleRadioButton = findViewById(R.id.male)
        femaleRadioButton = findViewById(R.id.female)
        otherGenderRadioButton = findViewById(R.id.otherGender)
        saveButton = findViewById(R.id.saveButtonSecondActivity)
        deleteButton = findViewById(R.id.deleteButton1)

        // Nhận dữ liệu của sinh viên được chọn từ Intent
        val fullname = intent.getStringExtra("fullname")
        val dob = intent.getStringExtra("dob")
        val gender = intent.getStringExtra("gender")
        val classroom = intent.getStringExtra("classroom")

        // Hiển thị dữ liệu của sinh viên trong giao diện để người dùng có thể chỉnh sửa
        fullnameEditText.setText(fullname)
        dobEditText.setText(dob)
        textViewClassroom.text = classroom
        Log.d("TAG", "Giá trị của classroom trong SA là: $classroom")

        imageViewClass.setOnClickListener {
            val intentClass = Intent(this, ClassActivity::class.java)
            startActivityForResult(intentClass, REQUEST_CODE_SELECT_CLASS)
        }

        // Xử lý nút Save để lưu lại hoặc nút Delete để xóa sinh viên
        saveButton.setOnClickListener {
            val editedFullName = fullnameEditText.text.toString()
            val editedDob = dobEditText.text.toString()
            val editedGender = when {
                maleRadioButton.isChecked -> "Male"
                femaleRadioButton.isChecked -> "Female"
                else -> "Other"
            }
            val editedClassroom = textViewClassroom.text.toString()

            // Tạo Intent để gửi lại dữ liệu đã chỉnh sửa về MainActivity
            val resultIntent = Intent().apply {
                putExtra("original_fullname", fullname)
                putExtra("edited_fullname", editedFullName)
                putExtra("dob", editedDob)
                putExtra("gender", editedGender)
                putExtra("classroom", editedClassroom)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Kết thúc SecondActivity và trở về MainActivity
        }

        deleteButton.setOnClickListener {
            val deletedFullName = fullnameEditText.text.toString()

            // Tạo Intent để thông báo về việc xóa sinh viên
            val resultIntent = Intent().apply {
                putExtra("action", "delete")
                putExtra("original_fullname", deletedFullName)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Kết thúc SecondActivity và trở về MainActivity
        }
    }

    // Xử lý kết quả trả về từ ClassActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_CLASS && resultCode == Activity.RESULT_OK) {
            // Nhận giá trị lớp học được chọn từ Intent
            selectedClassroom = data?.getStringExtra("classroom")
            Log.d("TAG", "Giá trị của selectedClassroom là: $selectedClassroom")
            // Hiển thị giá trị lớp học trong textViewClassroom
            textViewClassroom.text = selectedClassroom
        } else if (requestCode == REQUEST_CODE_EDIT_STUDENT && resultCode == Activity.RESULT_OK) {
            // Handle result from SecondActivity (editing or deleting a student)
            val action = data?.getStringExtra("action")
            val originalFullName = data?.getStringExtra("original_fullname")

            if (action == "delete") {
                // Tạo Intent để thông báo về việc xóa sinh viên và gửi lại kết quả cho MainActivity
                val resultIntent = Intent().apply {
                    putExtra("action", "delete")
                    putExtra("original_fullname", originalFullName)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Kết thúc SecondActivity và trở về MainActivity
            }
        }
    }
}
