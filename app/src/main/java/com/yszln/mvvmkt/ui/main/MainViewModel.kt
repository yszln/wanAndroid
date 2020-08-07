package com.yszln.mvvmkt.ui.main

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.viewmodel.BaseViewModel
import com.yszln.lib.viewmodel.LoadMoreViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.article.ArticleItemBean

class MainViewModel : LoadMoreViewModel() {

    var page: Int = 0

    val articleList = MutableLiveData<MutableList<ArticleItemBean>>()


    fun refreshHomeArticle() {
        page = 1
        launch(
            block = {
                val homeArticles = Api.mApiServer.getHomeArticles(page)
                LogUtil.e("datas->${homeArticles.data.size}")
                articleList.value = mutableListOf<ArticleItemBean>().apply {
                    addAll(homeArticles.data.datas)
                }
                refreshComplete()
            },
            error = {

            }

        )
    }

    fun loadHomeArticle() {
        page++;
        launch(
            block = {
                val homeArticles = Api.mApiServer.getHomeArticles(page)
                val mutableList = articleList.value ?: mutableListOf()
                mutableList.addAll(homeArticles.data.datas)
                LogUtil.e("datas->${homeArticles.data.size}")
                articleList.value = mutableList
                mLoadMoreStatus.value=System.currentTimeMillis().toInt()
            },
            error = {

            }

        )
    }
}