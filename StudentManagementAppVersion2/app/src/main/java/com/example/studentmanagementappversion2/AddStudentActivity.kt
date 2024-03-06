package com.example.studentmanagementappversion2

import Student
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream

class AddStudentActivity : AppCompatActivity() {
    private val classNames = arrayOf("21KTPM1", "21KTPM2", "21KTPM3") // Các lớp học
    private lateinit var classSpinner: Spinner
    private lateinit var saveButton: Button
    private lateinit var fullNameEditText: EditText
    private lateinit var birthdayEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var otherRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Ánh xạ các thành phần giao diện người dùng
        classSpinner = findViewById(R.id.classSpinner)
        saveButton = findViewById(R.id.saveButton1)
        fullNameEditText = findViewById(R.id.fullnameTextView)
        birthdayEditText = findViewById(R.id.dobTextView)
        genderRadioGroup = findViewById(R.id.radioGroup2)
        maleRadioButton = findViewById(R.id.male)
        femaleRadioButton = findViewById(R.id.female)
        otherRadioButton = findViewById(R.id.otherGender)

        // Thiết lập Adapter cho Spinner lựa chọn lớp học
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classNames)
        classSpinner.adapter = adapter

        // Xử lý sự kiện khi nhấn nút lưu
        saveButton.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val name = fullNameEditText.text.toString()
        val birthday = birthdayEditText.text.toString()
        val className = classSpinner.selectedItem.toString()
        val gender = when (genderRadioGroup.checkedRadioButtonId) {
            R.id.male -> "Male"
            R.id.female -> "Female"
            R.id.otherGender -> "Other"
            else -> ""
        }

        if (name.isEmpty() || birthday.isEmpty() || gender.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val student = Student(name, className, birthday, gender)

        // Lưu thông tin sinh viên vào tệp JSON
        val filePath = File(filesDir, "students.json").absolutePath
        val json = Json.encodeToString(student)
        val file = File(filePath)
        if (!file.exists()) {
            file.createNewFile()
        }
        val outputStream = FileOutputStream(file, true)
        outputStream.write(json.toByteArray())
        outputStream.close()

        // Trả về kết quả cho MainActivity để cập nhật danh sách sinh viên
        val resultIntent = intent.apply {
            putExtra("new_student_name", name)
            putExtra("new_student_classroom", className)
            putExtra("new_student_birthday", birthday)
            putExtra("new_student_gender", gender)
        }
        setResult(RESULT_OK, resultIntent)

        Toast.makeText(this, "Student saved successfully", Toast.LENGTH_SHORT).show()
        finish() // Trở về MainActivity sau khi lưu sinh viên thành công
    }
}
