package com.yszln.mvvmkt.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.yszln.lib.utils.ScreenUtil

class MaxHeightLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var maxHeight = 0

    init {
        maxHeight = (ScreenUtil.getScreenHeight() * 0.6).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (measuredHeight > maxHeight) {
            setMeasuredDimension(measuredWidth, maxHeight+paddingTop+paddingBottom)
        }
    }
}