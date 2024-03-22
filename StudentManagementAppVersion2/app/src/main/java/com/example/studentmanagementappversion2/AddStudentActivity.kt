package com.example.studentmanagementappversion2

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddStudentActivity : AppCompatActivity(), NoticeDialogListener{
    private val classNames = arrayOf("21KTPM1", "21KTPM2", "21KTPM3") // Các lớp học
    private lateinit var classBtn: TextView
    private lateinit var saveButton: Button
    private lateinit var fullNameEditText: EditText
    private lateinit var birthdayTV: TextView
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var otherRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Ánh xạ các thành phần giao diện người dùng
        classBtn = findViewById(R.id.classBtn)
        saveButton = findViewById(R.id.saveButton1)
        fullNameEditText = findViewById(R.id.fullnameTextView)
        birthdayTV = findViewById(R.id.dobTextView)
        genderRadioGroup = findViewById(R.id.radioGroup2)
        maleRadioButton = findViewById(R.id.male)
        femaleRadioButton = findViewById(R.id.female)
        otherRadioButton = findViewById(R.id.otherGender)

        // Xử lý khi nhấn vào EditText Birthday
        birthdayTV.setOnClickListener {
            showDatePickerDialog()
        }

        classBtn.setOnClickListener{
            val dialog = PopupFragment()
            dialog.show(supportFragmentManager, "PopupFragment")
        }

        // Xử lý sự kiện khi nhấn nút lưu
        saveButton.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val name = fullNameEditText.text.toString()
        val birthday = birthdayTV.text.toString()
        val className = classBtn.text.toString()
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

        // Trả về kết quả cho MainActivity để cập nhật danh sách sinh viên
        val resultIntent = intent.apply {
            putExtra("new_student_name", name)
            putExtra("new_student_classroom", className)
            putExtra("new_student_birthday", birthday)
            putExtra("new_student_gender", gender)
        }
        setResult(RESULT_OK, resultIntent)
        Log.d("AddStudentActivity", "Student saved successfully")
        Toast.makeText(this, "Student saved successfully", Toast.LENGTH_SHORT).show()
        finish() // Trở về MainActivity sau khi lưu sinh viên thành công
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Đặt ngày tháng đã chọn vào EditText
                val calendar = Calendar.getInstance()
                calendar.set(year, monthOfYear, dayOfMonth)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = sdf.format(calendar.time)
                birthdayTV.setText(formattedDate)
            },
            // Đặt ngày tháng mặc định cho DatePicker (có thể là ngày hiện tại)
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, selectedItems: String)
    {
        classBtn.text = selectedItems
    }

}
