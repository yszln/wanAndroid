package com.yszln.mvvmkt.widget.cate

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yszln.lib.adapter.CommonAdapter
import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.lib.utils.ScreenUtil
import com.yszln.mvvmkt.R

class CateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var mAdapter = object : CommonAdapter<CateItemBean>(R.layout.item_rv_cate) {
        override fun convert(holder: CommonViewHolder, item: CateItemBean) {
            holder.setText(R.id.textView, item.title)
            holder.itemView.layoutParams = ViewGroup.LayoutParams(
                (ScreenUtil.getScreenWidth() - paddingLeft - paddingRight) / itemCount,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    init {
        adapter = mAdapter
    }


    private var itemCount = 4

    fun setData(list: List<CateItemBean>) {
        list?.run {
            layoutManager = if (size >= 2 * itemCount) {
                //两排
                GridLayoutManager(context, 2, HORIZONTAL, false)
            } else {
                //一排
                GridLayoutManager(context, 1, HORIZONTAL, false)

            }
            mAdapter.setList(list)
        }
    }
}