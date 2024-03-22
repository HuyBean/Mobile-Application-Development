package com.example.studentmanagementappversion2

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class UpdateStudentActivity : AppCompatActivity(), NoticeDialogListener {
    private lateinit var fullNameTV: EditText
    private lateinit var dobTV: TextView
    private lateinit var classTV: TextView
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
        classTV = findViewById(R.id.classTV)
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

        classTV.setOnClickListener{
            val dialog = PopupFragment()
            dialog.show(supportFragmentManager, "PopupFragment")
        }

        when (studentGender) {
            "Male" -> maleButton.isChecked = true
            "Female" -> femaleButton.isChecked = true
            "Other" -> otherButton.isChecked = true
        }

        // Xử lý khi nhấn vào EditText Birthday
        dobTV.setOnClickListener {
            showDatePickerDialog()
        }

        // Xử lý khi nhấn nút Save
        saveButton.setOnClickListener {
            // Trả về kết quả với dữ liệu đã cập nhật
            val resultIntent = Intent().apply {
                putExtra("student_id", intent.getIntExtra("student_id", -1))
                putExtra("edit_student_name", fullNameTV.text.toString())
                putExtra("edit_student_classroom", classTV.text.toString())
                putExtra("edit_student_birthday", dobTV.text.toString())
                putExtra("edit_student_gender", when {
                    maleButton.isChecked -> "Male"
                    femaleButton.isChecked -> "Female"
                    else -> "Other"
                })
            }
            setResult(Activity.RESULT_OK, resultIntent)
            Toast.makeText(this, "Student has been updated", Toast.LENGTH_SHORT).show()
            finish() // Kết thúc UpdateStudentActivity
        }

        // Xử lý khi nhấn nút Delete
        deleteButton.setOnClickListener {
            // Hiển thị hộp thoại xác nhận xóa
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirm Delete")
            builder.setMessage("Are you sure you want to delete this student?")
            builder.setPositiveButton("Yes") { _, _ ->
                // Gửi dữ liệu để xóa
                val resultIntent = Intent().apply {
                    putExtra("student_id", intent.getIntExtra("student_id", -1))
                    putExtra("delete_student_name", studentName)
                    putExtra("delete_student_classroom", studentClassroom)
                    putExtra("delete_student_birthday", studentBirthday)
                    putExtra("delete_student_gender", studentGender)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                Toast.makeText(this, "Student has been deleted", Toast.LENGTH_SHORT).show()
                finish() // Kết thúc UpdateStudentActivity
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    // Hàm hiển thị DatePicker
    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Đặt ngày tháng đã chọn vào EditText
                val calendar = Calendar.getInstance()
                calendar.set(year, monthOfYear, dayOfMonth)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = sdf.format(calendar.time)
                dobTV.setText(formattedDate)
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
        classTV.text = selectedItems
    }
}
