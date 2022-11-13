package fsa.android.nasa.animation.system.visualization

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import fsa.android.nasa.R
import fsa.android.nasa.animation.system.core.*

class SystemTreeCanvas(context: Context?, attrs: AttributeSet?) : SystemCanvas(context, attrs) {
    enum class Mode{ EMPTY,FILLED }
    private var mode = Mode.EMPTY

    fun modeFilled()  { mode = Mode.FILLED }
    fun modeEmpty()   { mode = Mode.EMPTY }

    enum class Tree{ ONE,TWO }
    private var tree = Tree.TWO

    fun treeOne()  { tree = Tree.ONE }
    fun treeTwo()  { tree = Tree.TWO }

    override fun getTop(width: Int, height: Int, numberOfPoints: Int): Top {
        when(tree){
           Tree.ONE -> return TopTreeOne(width,height,numberOfPoints)
           Tree.TWO -> return TopTreeTwo(width,height,numberOfPoints)
        }
    }

    override fun drawing(canvas: Canvas){
        if(top.step >1){
            when(mode){
                Mode.EMPTY   -> empty  (canvas)
                Mode.FILLED  -> filled (canvas)
            }
        }
    }

    private fun empty(canvas: Canvas){
        canvasSwitch = canvasBit

        backGround(ContextCompat.getColor(context, R.color.color_deepest_blue))
        allHearts(0, Color.RED)

        canvasSwitch = canvas
        canvas.drawBitmap(bitmap,0f,0f,null)


        connectAllPoints(0, Color.WHITE)

        allPoints(1, Color.WHITE)
    }

    private fun filled(canvas: Canvas){
        canvasSwitch = canvasBit

        backGround(Color.BLUE)
        allHearts(0, Color.RED)
        connectAllPoints(0,Color.GREEN)

        canvasSwitch = canvas
        canvas.drawBitmap(bitmap,0f,0f,null)


        connectAllPoints(0, Color.BLACK)

        allPoints(1, Color.BLACK)
    }

}