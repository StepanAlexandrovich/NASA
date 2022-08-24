package fsa.android.nasa.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import coil.load
import fsa.android.nasa.R
import fsa.android.nasa.default

fun displayTextOnTextView(text:String?, textView: TextView){
    if(text.isNullOrEmpty()){
        textView.text = default
    } else {
        textView.text = text
    }
}

fun displayPictureOnImageView(url:String?, imageView: ImageView, context: Context){
    if (url.isNullOrEmpty()) {
        Toast.makeText(context,"PictureUrl is empty", Toast.LENGTH_SHORT)
    } else {
        imageView.load(url) {
            //lifecycle(this@PictureOfTheDayFragment) -> ПРИШЛОСЬ УБРАТЬ, горит красным this@PictureOfTheDayFragment, пока не разобрался

            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
            crossfade(true)
        }
    }
}