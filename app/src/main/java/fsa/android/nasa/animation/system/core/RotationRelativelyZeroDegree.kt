package fsa.android.nasa.animation.system.core

import kotlin.math.cos
import kotlin.math.sin

class RotationRelativelyZeroDegree():RotationRelativelyZero() {
    private var speed = 0
    private var distance = 0
    private var radius = 50

    override fun setSpeed(speed: Int) {
        this.speed = speed
    }

    fun setDistance(distance: Int) {
        this.distance = distance
    }

    override fun getDistance():Int {
        return distance
    }

    fun setRadius(radius:Int){
        if(radius>=0){ this.radius = radius }
    }

    override fun calculation() {
        distance += speed
        if(distance == 360){ distance = 0 }
        val rad = Math.toRadians(-distance.toDouble())
        x[next] = (sin(rad) * radius).toFloat()
        y[next] = (cos(rad) * radius).toFloat()
    }

}