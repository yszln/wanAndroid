package com.yszln.mvvmkt.ui.main.mine.bean

import com.yszln.mvvmkt.api.PageBean
import com.yszln.mvvmkt.ui.article.ArticleItemBean

data class MyArticleBean(
    val coinInfo: CoinInfo,
    val shareArticles: PageBean<ArticleItemBean>
)

data class CoinInfo(
    val coinCount: Int,
    val level: Int,
    val rank: String,
    val userId: Int,
    val username: String
)
