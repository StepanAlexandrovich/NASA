package fsa.android.nasa.animation.system.core

class TopTreeOne(width:Int, height: Int, numberOfCircles:Int):Top(width,height,numberOfCircles) {
    init {
        recursion("tree*",pointStatic,0,0,+1)
    }
}