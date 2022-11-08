package fsa.android.nasa.animation.system.core

abstract class Top (private val width:Int,private val height: Int,private val numberOfCircles:Int){
    var pointStatic = PointStatic(width.toFloat() / 2, height.toFloat() / 2)
    var pointsDynamic = arrayListOf<RotationRelativelyXY>()

    var step = 0

    fun recursion(mode:String,parent: Point,speed:Int,index: Int,start:Int){
        val point = RotationRelativelyXY().apply {
            setYRelativelyParent((start*width.toFloat())/(2*(numberOfCircles)))
            setSpeed(speed)
            setParent(parent)
        }
        pointsDynamic.add(point)

        if(index<numberOfCircles - 1){
            if(mode == "line"){
                recursion(mode,point as Point,speed + 1,index + 1,start)
            }else
            if(mode == "tree*"){
                recursion(mode,point as Point,+(index + 1),index + 1,start)
                recursion(mode,point as Point,-(index + 1),index + 1,start)
            }else
            if(mode == "tree**"){
                recursion(mode,point as Point,speed + 2,index + 1,start)
                recursion(mode,point as Point,speed - 2,index + 1,start)
            }
        }
    }

    fun getNumberOfCircles(): Int {
        return pointsDynamic.size
    }

    fun getWidth(): Int {
        return width
    }

    fun getHeight(): Int {
        return height
    }

    fun getXCenter(): Float {
        return pointStatic.getX()
    }

    fun getYCenter(): Float {
        return pointStatic.getY()
    }

    fun getX(index: Int): Float {
        return pointsDynamic[index].getX()
    }

    fun getXBack(index: Int): Float {
        return pointsDynamic[index].getXBack()
    }

    fun getXParent(index: Int): Float {
        return pointsDynamic[index].getXParent()
    }

    fun getY(index: Int): Float {
        return pointsDynamic[index].getY()
    }

    fun getYBack(index: Int): Float {
        return pointsDynamic[index].getYBack()
    }

    fun getYParent(index: Int): Float {
        return pointsDynamic[index].getYParent()
    }

    operator fun next() {
        for (point in pointsDynamic) {
            point.next()
        }
    }

    fun process() {
        step++
        for (point in pointsDynamic) {
            point.process()
        }
    }

}