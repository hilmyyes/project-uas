package com.example.projectuas.view

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.example.projectuas.R

@BindingAdapter("android:imagesUrl")
fun loadImage(v:ImageView, url: String?){
    val parent = v.parent as? View
    var loadImage = parent?.findViewById<ProgressBar>(R.id.loadImage)

    if (url.isNullOrEmpty()) {
        v.visibility = View.GONE
    } else {
        val picasso = Picasso.Builder(v.context)
        picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
        picasso.build().load(url).into(v)
        v.visibility = View.VISIBLE
        if(loadImage != null){
            loadImage.visibility = View.GONE
        }
    }
}
