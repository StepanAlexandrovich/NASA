package fsa.android.nasa.animation.system.core

open class RotationRelativelyZeroComposition():RotationRelativelyZero() {
    private var radius = 0

    enum class Mode{ INVERSION,DEGREE }
    companion object{
        var mode = Mode.INVERSION
        fun modeInversion() { mode = Mode.INVERSION }
        fun modeDegree()    { mode = Mode.DEGREE    }
    }

    private var rotationRelativelyZero:RotationRelativelyZero = when(mode){
        Mode.INVERSION -> RotationRelativelyZeroInversion()
        Mode.DEGREE -> RotationRelativelyZeroDegree()
    }

    fun setRadius(radius: Int):RotationRelativelyZeroComposition {
        if(radius>=0){ this.radius = radius }
        return this
    }

    fun startTop(){
        rotationRelativelyZero.setY(+radius.toFloat())
        if(mode == Mode.DEGREE){
            setStartModeDegree(0,radius)
        }
    }

    fun startBottom(){
        rotationRelativelyZero.setY(-radius.toFloat())
        if(mode == Mode.DEGREE){
            setStartModeDegree(180,radius)
        }
    }

    override fun setSpeed(speed: Int) {
        rotationRelativelyZero.setSpeed(speed)
    }

    override fun getDistance():Int{
        return rotationRelativelyZero.getDistance()
    }

    // override
    override fun process() {
        rotationRelativelyZero.process()
    }

    override fun calculation() {
        rotationRelativelyZero.calculation()
    }

    override fun getX(): Float {
        return rotationRelativelyZero.getX()
    }

    override fun getY(): Float {
        return rotationRelativelyZero.getY()
    }

    override fun getXBack(): Float {
        return rotationRelativelyZero.getXBack()
    }

    override fun getYBack(): Float {
        return rotationRelativelyZero.getYBack()
    }

    private fun setStartModeDegree(distance:Int,radius: Int){
        val rot = rotationRelativelyZero as RotationRelativelyZeroDegree
        rot.setRadius(radius)
        rot.setDistance(distance)
    }

}