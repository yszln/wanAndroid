package com.yszln.lib.widget.title

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.yszln.lib.R
import kotlinx.android.synthetic.main.view_title.view.*

class TitleBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    fun setTitle(title: String?) {
        titleTitle.text = title
    }

    init {
        View.inflate(context, R.layout.view_title, this)

        titleBlack.setOnClickListener {
            if (context is Activity) {
                context.finish()
            }
        }
    }
}