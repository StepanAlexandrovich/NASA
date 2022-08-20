package fsa.android.nasa.launch

import android.app.Application

class LaunchApplication:Application() {
    companion object {
        private var myApp: LaunchApplication? = null
        fun getMyApp() = myApp!!
    }

    override fun onCreate() {
        super.onCreate()
        myApp = this
    }
}