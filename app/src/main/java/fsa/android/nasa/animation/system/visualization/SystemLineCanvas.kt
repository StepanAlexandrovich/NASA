package fsa.android.nasa.animation.system.visualization

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import fsa.android.nasa.animation.system.core.Top
import fsa.android.nasa.animation.system.core.TopLine

class SystemLineCanvas(context: Context?, attrs: AttributeSet?) : SystemCanvas(context, attrs) {
    enum class Mode{ HOLE,UFO,HEART }
    private var mode = Mode.HOLE

    fun modeHole()  { mode = Mode.HOLE }
    fun modeUfo()   { mode = Mode.UFO }
    fun modeHeart() { mode = Mode.HEART }

    override fun getTop(width: Int, height: Int, numberOfPoints: Int): Top {
        return TopLine(width,height,numberOfPoints)
    }

    override fun drawing(canvas: Canvas){
        if(top.step >1){
            when(mode){
                Mode.HOLE  -> hole (canvas)
                Mode.UFO   -> ufo  (canvas)
                Mode.HEART -> heart(canvas)
            }
        }
    }

    private fun hole(canvas: Canvas){
        canvasSwitch = canvasBit

        backGround(Color.CYAN)
        allHearts(1,Color.RED)
        connectAllPoints(1,Color.BLACK)

        canvasSwitch = canvas
        canvas.drawBitmap(bitmap,0f,0f,null)

        allPoints(1,Color.RED)
    }

    private fun ufo(canvas: Canvas){
        canvasSwitch = canvasBit

        if(top.step<632){
            backGround(Color.BLUE)
            allHearts(0,Color.RED) // или Green
            firstPointWithCenter(Color.BLACK)
        }
        canvasSwitch = canvas
        canvas.drawBitmap(bitmap,0f,0f,null)
        allPoints(1,Color.BLACK)
    }

    private fun heart(canvas: Canvas){
        canvasSwitch = canvasBit

        backGround(Color.BLUE)
        allHearts(0,Color.RED)
        connectAllPoints(0,Color.RED)
        firstPointWithCenter(Color.RED)

        canvasSwitch = canvas
        canvas.drawBitmap(bitmap,0f,0f,null)

        firstPointWithCenter(Color.BLACK)
        connectAllPoints(0,Color.BLACK)
        if(top.getX(top.getNumberOfCircles() - 1) < top.getXCenter()+15
            &&
            top.getX(top.getNumberOfCircles() - 1) > top.getXCenter()-15
        ){
            firstPointWithCenter(Color.GREEN)
            connectAllPoints(0,Color.GREEN)
        }

        allPoints(0,Color.BLACK)
    }
}