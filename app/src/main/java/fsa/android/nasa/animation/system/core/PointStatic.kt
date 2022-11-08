package fsa.android.nasa.animation.system.core

class PointStatic(private var x:Float,private var y:Float):Point{

    override fun getX(): Float {
        return x.toFloat()
    }

    override fun getY(): Float {
        return y.toFloat()
    }

    override fun setX(x: Float) {
        this.x = x
    }

    override fun setY(y: Float) {
        this.y = y
    }
}