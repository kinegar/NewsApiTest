package iglo.indocyber.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object GlideBinding{
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, link: String?) {
        link?.let {
            Glide.with(imageView)
                .load(link)
                .into(imageView)
        }
    }
}
