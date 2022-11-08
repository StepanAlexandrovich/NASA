package fsa.android.nasa.animation.system.core

class TopLine(width:Int,height: Int,numberOfCircles:Int):Top(width,height,numberOfCircles) {
    init {
        recursion("line",pointStatic,1,1,1)
    }
}