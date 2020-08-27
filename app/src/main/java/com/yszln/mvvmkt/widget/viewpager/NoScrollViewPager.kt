package com.yszln.mvvmkt.widget.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoScrollViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    //禁止滑动
    override fun onInterceptTouchEvent(ev: MotionEvent?) = false

    override fun setCurrentItem(item: Int) {
        //取消动画
        super.setCurrentItem(item, false)
    }
}