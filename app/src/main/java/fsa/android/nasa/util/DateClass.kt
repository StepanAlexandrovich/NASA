package fsa.android.nasa.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun stringDateToday():String{
    return stringDate(0)
}

@RequiresApi(Build.VERSION_CODES.O)
fun stringDateYesterday():String{
    return stringDate(-1)
}

@RequiresApi(Build.VERSION_CODES.O)
fun stringDateTheDayBeforeYesterday():String{
    return stringDate(-2)
}

@RequiresApi(Build.VERSION_CODES.O)
fun stringDate(value:Long):String{
    var date = LocalDate.now()
    if(value>0) { date = date.plusDays (+value) }else
        if(value<0) { date = date.minusDays(-value) }
    return date.toString()
}