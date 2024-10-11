package hwsein.developer.example.mynote.CustomView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.io.path.Path

class PenView(
    context : Context ,
    attr : AttributeSet
) : View(context , attr) {

    private var paint = Paint()
    private var path = Path()


    init {

        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 10f

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {


        val x = event?.x
        val y = event?.y

        when(event?.action){

            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x!!, y!!)
                return true
            }

            MotionEvent.ACTION_MOVE -> {

                path.lineTo(x!! , y!!)
                invalidate()

            }

            MotionEvent.ACTION_UP -> {

            }
            else -> false
        }
        invalidate()
        return true
    }

    fun changeColor(newColor : Int){

        paint.color = newColor

    }

    fun changeSize(newSize : Float){

        paint.strokeWidth = newSize

    }

    fun clear(){

        path.reset()
        invalidate()

    }


}