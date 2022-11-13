package fsa.android.nasa.animation.text

import android.app.Activity
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import fsa.android.nasa.R
import fsa.android.nasa.util.coloredText
import java.util.*

class TextAnimationGlobe(val textView: TextView,val text:String, private val activity: Activity):TimerTask() {
    private var step = 0
    private var rotation = 0
    private val spannableString = SpannableString(text)
    private var process = false

    init{
        coloredText(spannableString,Color.GREEN)
        earthReplacesO(spannableString)

        textView.text = spannableString
    }

    private val timer = Timer()
    fun start(){
        timer.scheduleAtFixedRate(this,0,25)
    }
    fun stop(){
        timer.cancel()
    }

    private fun earthReplacesO(spannableString: SpannableString): SpannableString {

        val bitmap = ContextCompat.getDrawable(activity, R.drawable.globe_earth)!!.toBitmap()

        for(i in text.indices){
            if(text[i] == 'O'){
                spannableString.setSpan(
                    ImageSpan(activity,bitmap),
                    i,i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE )
            }
        }

        return spannableString
    }

    fun switchProcess(){
        process = !process
    }

    override fun run() {
        if(process){
            if(rotation%5==0){
                coloredText(spannableString,Color.GREEN)
                spannableString.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(activity, R.color.cherry)),
                    step,step+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE )
                step++
                if(step==text.length){
                    step = 0
                }
                textView.text = spannableString
            }

            rotation++
            if(rotation == 360){
                rotation = 0
            }
        }
    }
}