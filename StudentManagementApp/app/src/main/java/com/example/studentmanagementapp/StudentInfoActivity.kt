package com.example.studentmanagementapp

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class StudentInfoActivity : AppCompatActivity() {
    private lateinit var fullnameEditText: EditText
    private lateinit var dobEditText: EditText
    private lateinit var imageViewClass: ImageView
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var otherGenderRadioButton: RadioButton

//    private val classActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val data: Intent? = result.data
//            val selectedValue = data?.getStringExtra("selectedValue")
//            classroomButton.text = selectedValue
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)

        fullnameEditText = findViewById(R.id.fullnameTextView)
        dobEditText = findViewById(R.id.dobTextView)
        imageViewClass = findViewById(R.id.imageViewClass)
        maleRadioButton = findViewById(R.id.male)
        femaleRadioButton = findViewById(R.id.female)
        otherGenderRadioButton = findViewById(R.id.otherGender)

        imageViewClass.setOnClickListener {
            val intentClass = Intent(this, ClassActivity::class.java)
            startActivity(intentClass)
        }

        val saveButton = findViewById<Button>(R.id.saveButton1)
        saveButton.setOnClickListener {
            val fullname = fullnameEditText.text.toString()
            val dob = dobEditText.text.toString()
//            val classroom = imageViewClass.text.toString()
            val gender = when {
                maleRadioButton.isChecked -> "Male"
                femaleRadioButton.isChecked -> "Female"
                else -> "Other"
            }

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("fullname", fullname)
                putExtra("dob", dob)
//                putExtra("classroom", classroom)
                putExtra("gender", gender)
            }
            startActivity(intent)
        }
    }
}
