package com.yszln.mvvmkt.ui.main.home.vm

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.viewmodel.LoadMoreViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.article.ArticleItemBean

class HomeViewModel: LoadMoreViewModel() {

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
                val homeArticles = Api.mApiServer.getHomeArticles(page).data.datas
                articleList.value?.apply {
                    addAll(homeArticles)
                }
                mLoadMoreStatus.value = System.currentTimeMillis().toInt()
            },
            error = {

            }

        )
    }
}