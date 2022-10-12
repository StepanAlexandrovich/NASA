package fsa.android.nasa.launch

import android.app.Application
import fsa.android.nasa.KEY_THEME
import fsa.android.nasa.util.SaveStringImpl

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        savedTheme = SaveStringImpl(KEY_THEME,"DEFAULT",this)
    }

    companion object {
        private var savedTheme: SaveStringImpl? = null

        fun getSavedTheme() = savedTheme!!
    }
}