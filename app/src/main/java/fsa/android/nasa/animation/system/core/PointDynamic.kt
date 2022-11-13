package fsa.android.nasa.animation.system.core

abstract class PointDynamic:Point{
    var x = floatArrayOf(0.0F, 0.0F)
    var y = floatArrayOf(0.0F, 0.0F)

    var table = intArrayOf(1, 0)
    var now = 0
    var next = table[now]

    // get set //
    override fun getX(): Float {
        return x[now]
    }
    override fun getY(): Float {
        return y[now]
    }
    override fun setX(x: Float) {
        this.x[now] = x
    }
    override fun setY(y: Float) {
        this.y[now] = y
    }

    open fun getXBack(): Float {
        return x[next]
    }
    open fun getYBack(): Float {
        return y[next]
    }

    // process //
    abstract fun calculation()

    fun next() {
        now = table[now]
        next = table[next];
    }

    open fun process() {
        calculation()
        next()
    }

}