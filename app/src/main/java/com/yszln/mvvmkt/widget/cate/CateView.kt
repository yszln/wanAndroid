package com.yszln.mvvmkt.widget.cate

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.yszln.lib.adapter.CommonAdapter
import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.utils.ScreenUtil
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.project.ProjectActivity

class CateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var itemCount = 5
    var itemWidth = 0
    val recyclerView: RecyclerView
    val cateProgressView: CateProgressView

    init {
        itemWidth = (ScreenUtil.getScreenWidth() - paddingLeft - paddingRight) / itemCount
        recyclerView = RecyclerView(context)
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        recyclerView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        cateProgressView = CateProgressView(context)
        cateProgressView.layoutParams =
            LayoutParams(ScreenUtil.dpToPx(40f), ScreenUtil.dpToPx(3f))
        addView(recyclerView)
        addView(cateProgressView)
    }

    private var mAdapter = object : CommonAdapter<CateItemBean>(R.layout.item_rv_cate) {
        override fun convert(holder: CommonViewHolder, item: CateItemBean) {
            holder.setText(
                R.id.textView,
                HtmlCompat.fromHtml(item.name, HtmlCompat.FROM_HTML_MODE_LEGACY)
            )
            holder.itemView.layoutParams = ViewGroup.LayoutParams(
                itemWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
    var mScrollPosition = 0;

    init {


        recyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                val courseId = mAdapter.data[position].id
                val name = mAdapter.data[position].name
                val intent = Intent(context, ProjectActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("cate", courseId)
                bundle.putString("title", name)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener(object : OnScrollChangeListener {
                override fun onScrollChange(p0: View?, p1: Int, p2: Int, p3: Int, p4: Int) {
                    mScrollPosition -= p3
                    LogUtil.e("mScrollPosition:${mScrollPosition},widthCount:${lineCount * itemWidth + paddingLeft + paddingRight}")
                    val ratio =
                        mScrollPosition.toFloat() / (lineCount * itemWidth + paddingLeft + paddingRight - ScreenUtil.getScreenWidth())
                    cateProgressView.setProgress(ratio)
                }
            })
        }


    }

    var lineCount = 0

    fun setData(list: List<CateItemBean>) {
        list?.run {
            recyclerView.layoutManager = if (size >= 2 * itemCount) {
                lineCount = (size / 2f + .5f).toInt()
                //两排
                GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

            } else {
                lineCount = size
                //一排
                GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            }
            mAdapter.setList(list)

        }

    }
}