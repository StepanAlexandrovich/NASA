package fsa.android.nasa.util

import androidx.appcompat.app.AppCompatActivity
import fsa.android.nasa.R

fun setThemeNasa(stringThemeOut:String,activity: AppCompatActivity){
    when(stringThemeOut){
        "DEFAULT" -> activity.setTheme(R.style.Theme_Nasa)
        "MATERIAL DAY" -> activity.setTheme(com.google.android.material.R.style.Base_V24_Theme_Material3_Dark)
        "MATERIAL NIGHT" -> activity.setTheme(com.google.android.material.R.style.Base_V24_Theme_Material3_Light)
        "INDIGO" -> activity.setTheme(R.style.IndigoTheme)
        "PINK" -> activity.setTheme(R.style.PinkTheme)
    }
}