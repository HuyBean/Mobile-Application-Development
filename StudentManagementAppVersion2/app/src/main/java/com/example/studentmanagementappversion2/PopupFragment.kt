package com.example.studentmanagementappversion2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

interface NoticeDialogListener {
    fun onDialogPositiveClick(dialog: DialogFragment, selectedItems: String)
}

class PopupFragment : DialogFragment() {
    internal lateinit var listener: NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try { // Verify that the host activity implements the callback interface
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement NoticeDialogListener")
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            var selectedClass: String = ""
            val classList = arrayOf("21KTPM1", "21KTPM2", "21KTPM3", "21KTPM4")
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Dialog with checkboxes")
                // Specify the list array
                .setSingleChoiceItems(classList, -1,
                    DialogInterface.OnClickListener { dialog, which ->
                        selectedClass = classList[which]
                    })
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                    listener.onDialogPositiveClick(this, selectedClass)
                    Log.d("Popup", "User clicked OK, so save the selectedItems...")
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}