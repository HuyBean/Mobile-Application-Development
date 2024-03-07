package com.example.photogallerysdcard

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class PictureAdapter(private val context: Context, private val imagePaths: List<String>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var imageView: ImageView? = null

        init {
             this.imageView = row?.findViewById(R.id.imageView)
        }
    }

    override fun getCount(): Int {
        return imagePaths.size
    }

    override fun getItem(position: Int): Any {
        return imagePaths[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            // Inflate the layout for each item
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.picture_layout, parent, false)

            // Create a ViewHolder and store references to the children views
            viewHolder = ViewHolder(view)
            viewHolder.imageView = view.findViewById(R.id.imageView)

            // Store the ViewHolder object as a tag of the view
            view.tag = viewHolder
        } else {
            // View is being recycled, retrieve the ViewHolder object from tag
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // Load Bitmap into ImageView using imagePaths[position]
        val bitmap = BitmapFactory.decodeFile(imagePaths[position])
        viewHolder.imageView?.setImageBitmap(bitmap)

        return view
    }
}