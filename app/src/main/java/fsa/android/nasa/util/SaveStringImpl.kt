package fsa.android.nasa.util

import android.content.Context
import android.content.SharedPreferences

class SaveStringImpl(private val key:String, context: Context):SaveString {
    private var sp: SharedPreferences = context.getSharedPreferences(key,Context.MODE_PRIVATE)

    override fun read(): String {
        return sp.getString(key,"").toString()
    }

    override fun write(b:String) {
        sp.edit().putString(key,b).apply()
    }
}