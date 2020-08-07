package com.yszln.mvvmkt.ui.main

import com.yszln.lib.adapter.CommonViewHolder
import com.yszln.lib.adapter.LoadMoreAdapter
import com.yszln.mvvmkt.R
import com.yszln.mvvmkt.ui.article.ArticleItemBean

class ArticleAdapter : LoadMoreAdapter<ArticleItemBean>(R.layout.item_rv_home_article) {
    override fun convert(holder: CommonViewHolder, item: ArticleItemBean) {
        holder.setText(R.id.item_rv_home_no, holder.adapterPosition.toString() + item.title)
    }
}