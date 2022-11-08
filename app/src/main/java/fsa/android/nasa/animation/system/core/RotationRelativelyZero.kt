package fsa.android.nasa.animation.system.core

class RotationRelativelyZero():PointDynamic() {
    private val vectorAbs = 0.01f
    private var vector = vectorAbs
    private var circle = Math.sqrt(1 / (1.0 + vectorAbs*vectorAbs)).toFloat()
    private var speed = 0

    fun setSpeed(speed: Int) {
        var pol = 0
        if(speed>0) { pol = +1 }
        else        { pol = -1 }

        vector = vectorAbs*pol
        this.speed = speed*pol
    }

    override fun process() {
        for (i in 0 until speed) {
            super.process()
        }
    }

    override fun calculation() {
        x[next] = circle * (x[now] - vector * y[now])
        y[next] = circle * (y[now] + vector * x[now])
    }

}