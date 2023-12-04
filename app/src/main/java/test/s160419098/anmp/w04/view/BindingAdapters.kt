package test.s160419098.anmp.w04.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (url == null) return
    Picasso.Builder(imageView.context)
        .listener { _, _, exception -> exception.printStackTrace() }
        .build()
        .load(url)
        .into(imageView)
}