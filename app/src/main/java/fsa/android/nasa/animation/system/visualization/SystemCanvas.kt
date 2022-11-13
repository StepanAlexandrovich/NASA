package fsa.android.nasa.animation.system.visualization

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import fsa.android.nasa.animation.system.core.*

abstract class SystemCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // logic
    private var numberOfPoints = 5
    lateinit var top: Top
    // drawing
    private val paint = Paint()
    lateinit var bitmap: Bitmap
    lateinit var canvasBit:Canvas
    lateinit var canvasSwitch:Canvas
    // control
    private var process = false

    var startInit = false
    private fun startInit(canvas: Canvas){
        if(!startInit){

            top = getTop(canvas.width,canvas.height,numberOfPoints)
            bitmap = Bitmap.createBitmap(canvas.width,canvas.height,Bitmap.Config.ARGB_8888)
            canvasBit = Canvas(bitmap)

            startInit = true
        }
    }

    abstract fun getTop(width:Int,height:Int,numberOfPoints:Int):Top

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        startInit(canvas)

        if(process) { top.process()   }
        if(true)    { drawing(canvas) }

        invalidate()
    }

    abstract fun drawing(canvas: Canvas)

    // tools //
    fun heart(index:Int,color:Int){
        canvasSwitch.apply {
            drawLine(
                top.getXBack(index),
                top.getYBack(index),
                top.getX(index),
                top.getY(index),
                paint
            )
        }
    }

    fun allHearts(first:Int,color: Int) {
        paint.color = color
        for (i in first until top.getNumberOfCircles()) {
            heart(i,color)
        }
    }

    fun connectAllPoints(first:Int,color:Int){
        paint.color = color

        for (i in 1 + first until top.getNumberOfCircles()) {
            canvasSwitch.drawLine(
                top.getXParent(i),
                top.getYParent(i),
                top.getX(i),
                top.getY(i),
                paint
            )
        }
    }

    fun firstPointWithCenter(color:Int) {
        paint.color = color
        canvasSwitch.drawLine(
            top.getX(0),
            top.getY(0),
            top.getXCenter(),
            top.getYCenter(),
            paint
        )
    }

    fun allPoints(first: Int,color:Int) {
        paint.color = color

        for (i in first until top.getNumberOfCircles()) {
            canvasSwitch.drawCircle(
                top.getX(i) ,
                top.getY(i),
                10f,
                paint
            )
        }
    }

    fun backGround(color: Int) {
        if (top.step == 2) {
            paint.color = color
            canvasSwitch.drawRect(
                0f,
                0f,
                top.getWidth().toFloat(),
                top.getHeight().toFloat(),
                paint
            )
        }
    }


    // control
    fun restart(){
        process = true
        top = getTop(top.getWidth(),top.getHeight(),numberOfPoints)
    }

    fun switchProcess(){
        process = !process
    }

    fun plusPoint(){
        if(numberOfPoints<20){
            numberOfPoints++
        }
        restart()
    }

    fun minusPoint(){
        if(numberOfPoints>2){
            numberOfPoints--
        }
        restart()
    }

}