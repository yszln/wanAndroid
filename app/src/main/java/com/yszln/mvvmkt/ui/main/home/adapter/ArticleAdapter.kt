package com.yszln.mvvmkt.ui.main.home.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.article.ArticleItemBean
import com.yszln.mvvmkt.ui.common.CommonWebActivity

class ArticleAdapter : LoadMoreAdapter<ArticleItemBean>(R.layout.item_rv_home_article) {

    init {
        setOnItemClickListener { _: BaseQuickAdapter<*, *>, view: View, position: Int ->
            val intent = Intent(context, CommonWebActivity::class.java)
            intent.putExtra("url", data[position].link)
            context.startActivity(intent)
        }
    }

    override fun convert(holder: CommonViewHolder, item: ArticleItemBean) {
        holder.setText(
            R.id.item_rv_home_no,
            (holder.adapterPosition + 1).toString() + ".\u2000" + item.title
        )
    }
}