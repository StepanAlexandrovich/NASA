package fsa.android.nasa.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

class DateClass {

    companion object{
        @RequiresApi(Build.VERSION_CODES.O)
        fun stringDate(value:Long):String{
            var date = LocalDate.now()
            if(value>0) { date = date.plusDays (+value) }else
            if(value<0) { date = date.minusDays(-value) }
            return date.toString()
        }
    }

}