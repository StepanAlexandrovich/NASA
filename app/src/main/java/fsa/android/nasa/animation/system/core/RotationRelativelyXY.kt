package fsa.android.nasa.animation.system.core

class RotationRelativelyXY:PointDynamic() {
    private val rotation = RotationRelativelyZero()
    private lateinit var parent: Point

    fun getXParent():Float{
        return parent.getX()
    }

    fun getYParent():Float{
        return parent.getY()
    }

    fun setParent(parent: Point) {
        this.parent = parent
    }

    fun setYRelativelyParent(y:Float){
        rotation.setY(y)
    }

    fun setSpeed(speed: Int) {
        rotation.setSpeed(speed)
    }

    override fun calculation() {
        x[next] = parent.getX() + rotation.getX()
        y[next] = parent.getY() + rotation.getY()
        rotation.process()
    }
}