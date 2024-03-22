package com.example.photogallerysdcard

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Intent
import android.os.Environment
import android.util.Log
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import java.io.File

class MainActivity : ComponentActivity() {
    companion object {
        private val EXTERNAL_PERMS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        private const val EXTERNAL_REQUEST_CODE = 113
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (requestForPermission()) {
            readPicsFromSDCard()
        }


    }
    private fun requestForPermission(): Boolean {
        var isPermissionOn = true
        if (Build.VERSION.SDK_INT >= 23) {
            if (!canAccessExternalSd()) {
                isPermissionOn = false
                requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST_CODE)
            }
        }
        return isPermissionOn
    }

    private fun canAccessExternalSd(): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            EXTERNAL_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    useSDCard()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun useSDCard() {
        // Implement your logic for using the SD card here
    }

    private fun readPicsFromSDCard() {
        try {
            val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val sdcardPath = File(picturesDir, "pics01")

            if (sdcardPath.exists() && sdcardPath.isDirectory) {
                val files = sdcardPath.listFiles()

                val imagePaths = mutableListOf<String>()
                files?.forEach { file ->
                    imagePaths.add(file.absolutePath)
                }

                val gridView: GridView = findViewById(R.id.gridView)
                val adapter = PictureAdapter(this, imagePaths)
                gridView.adapter = adapter
                gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                    val selectedImagePath = imagePaths[position]
                    Log.d("ImagePath", selectedImagePath) // Add this line
                    val intent = Intent(this, DisplayActivity::class.java)
                    intent.putExtra("image_path", selectedImagePath)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "No pictures found in the specified directory", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            Toast.makeText(this, "Error reading pictures from SD card", Toast.LENGTH_SHORT).show()
        }
    }

}