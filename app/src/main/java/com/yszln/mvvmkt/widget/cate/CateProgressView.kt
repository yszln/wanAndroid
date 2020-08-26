package com.yszln.mvvmkt.widget.cate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.yszln.lib.utils.ResUtils
import com.yszln.mvvmkt.R

class CateProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var ratio = 0f
    var ratioWidth = 40f

    init {
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = ratioWidth

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        ratioWidth = measuredWidth /3f
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画背景
        mPaint.color = ResUtils.getColor(R.color.gray_ed)
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            height / 2f,
            height / 2f,
            mPaint
        )
        //画进度
        mPaint.color = ResUtils.getColor(R.color.blue)
        canvas.drawRoundRect(
            width * ratio - ratioWidth * ratio,
            0f,
            width * ratio + ratioWidth * (1 - ratio),
            height.toFloat(),
            height / 2f,
            height / 2f,
            mPaint
        )
    }

    fun setProgress(ratio: Float) {
        this.ratio = ratio
        invalidate()
    }
}