package fsa.android.nasa.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

/*
    код дублируется в первых трёх методах, но я не знаю как этого избежать, вы просили меня вынести магические числа из слушателя в главном фрагменте,
    в котором я непосредственно и вызывал метод stringDate(...) (внизу)
    метод stringDate я специально сделал универсальным , он возвращает строку типа ГОД МЕСЯЦ ДАТА относительно текущей даты на какое то
    число дней вперёд или назад в зависимости какое число я в него отправлю,
    число 0 - сегодня
    число -2 позавчера
    чило  +2 послезавтра и т д

    Ещё подскажите как мне от @RequiresApi максимально избавиться
*/

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