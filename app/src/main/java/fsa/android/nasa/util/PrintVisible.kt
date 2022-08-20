package fsa.android.nasa.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import fsa.android.nasa.launch.LaunchApplication

object PrintVisible {

    fun printLong(text:String){
        Toast.makeText(LaunchApplication.getMyApp(),text, Toast.LENGTH_LONG).show()
    }

    fun printLongThread(text:String){
        Thread{
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(LaunchApplication.getMyApp(),text, Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    fun printShortThread(text:String){
        Thread{
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(LaunchApplication.getMyApp(),text, Toast.LENGTH_LONG).show()
            }
        }.start()
    }

    fun printShort(text:String){
        Toast.makeText(LaunchApplication.getMyApp(),text, Toast.LENGTH_SHORT).show()
    }

}