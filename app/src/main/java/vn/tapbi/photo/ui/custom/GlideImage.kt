package vn.tapbi.photo.ui.custom

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import vn.tapbi.photo.R

object GlideImage {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(image_View: ImageView, path: String) {
        Glide.with(image_View)
            .load(path)
            .error(R.drawable.demo)
            .into(image_View)
    }
    @JvmStatic
    @BindingAdapter("setText")
    fun setText(txt_View: TextView, name: String) {
        txt_View.setText(name)
    }
}