package iglo.indocyber.common.ext

import android.view.View

fun View.hide(){
    this.visibility = View.GONE
}
fun View.visible(){
    this.visibility = View.VISIBLE
}