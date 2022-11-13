package fsa.android.nasa.animation.system.visualization

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import fsa.android.nasa.R
import fsa.android.nasa.animation.system.core.*

class HeartEarthCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // settings
    private var numberOfPoints = 10
    private val widthMatrix = 501
    private val heightMatrix = 501
    private val margin = 150
    private val widthWithMargin = widthMatrix + 2 * margin
    private val heightWithMargin = heightMatrix + 2 * margin

    // logic
    private var top = TopLine(widthMatrix,heightMatrix,numberOfPoints)
    // drawing
    private val paint = Paint()
    private val bitmapBottom = Bitmap.createBitmap(widthWithMargin,heightWithMargin,Bitmap.Config.ARGB_8888)
    private val canvasBitBottom = Canvas(bitmapBottom)
    private val bitmapTop = Bitmap.createBitmap(widthWithMargin,heightWithMargin,Bitmap.Config.ARGB_8888)
    private val canvasBitTop = Canvas(bitmapTop)
    private val bitmapGlobe = BitmapFactory.decodeResource(resources, R.drawable.globe_earth_1)
    private val radiusGlobe = bitmapGlobe.width/2
    private lateinit var canvasSwitch:Canvas
    // control
    private var process = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(process) { top.process()   }
        if(true)    { drawing(canvas) }

        invalidate()
    }

    private fun drawing(canvas: Canvas){
        if(top.step>1){
            val out = top.getNumberOfCircles() - 1

            canvasSwitch = canvasBitBottom

            backGround(ContextCompat.getColor(context, R.color.color_deepest_blue))
            heart(out,Color.RED)
            firstPointWithEndPoint(Color.RED)

            canvasSwitch = canvasBitTop
            canvasSwitch.drawBitmap(bitmapBottom,0f,0f,null)

            movingGlobe(top.getX(out)+margin,top.getY(out)+margin,top.pointsDynamic[out].getDistance())

            val bitmapMult = Bitmap.createScaledBitmap(bitmapTop,canvas.width,canvas.height, false)
            canvas.drawBitmap(bitmapMult,0f,0f,null)
        }
    }

    // tools //
    private fun heart(index:Int,color:Int){
        paint.color = color
        canvasSwitch.apply {
            drawLine(
                top.getXBack(index) + margin,
                top.getYBack(index) + margin,
                top.getX(index) + margin,
                top.getY(index) + margin,
                paint
            )
        }
    }

    private fun firstPointWithEndPoint(color:Int) {
        paint.color = color
        canvasSwitch.drawLine(
            top.getX(top.getNumberOfCircles() - 1) + margin,
            top.getY(top.getNumberOfCircles() - 1) + margin,
            top.getXCenter() + margin,
            top.getYCenter() + margin,
            paint
        )
    }

    fun backGround(color: Int) {
        if (top.step == 2) {
            paint.color = color
            canvasSwitch.drawRect(
                0f,
                0f,
                widthWithMargin.toFloat(),
                heightWithMargin.toFloat(),
                paint
            )
        }
    }

    private fun movingGlobe(x:Float,y:Float,rotate:Int){
        val matrix = Matrix()
        matrix.setRotate(rotate.toFloat(),x,y)
        matrix.preTranslate(x-radiusGlobe,y-radiusGlobe)

        canvasSwitch.drawBitmap(bitmapGlobe,matrix,null)
    }

    // control
    fun switchProcess(){
        process = !process
    }

    fun getProcess():Boolean{
        return process
    }

}