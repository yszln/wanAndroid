package com.yszln.mvvmkt.ui.main.home.vm

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.viewmodel.LoadMoreViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.article.ArticleItemBean
import com.yszln.mvvmkt.ui.main.home.bean.BannerItemBean

class HomeViewModel : LoadMoreViewModel() {

    var page: Int = 0

    val articleList = MutableLiveData<MutableList<ArticleItemBean>>()
    val bannerList = MutableLiveData<MutableList<BannerItemBean>>()


    fun refreshHomeArticle() {
        page = 1
        launch(
            block = {
                val homeArticles = Api.mApiServer.getHomeArticles(page)
                articleList.value = mutableListOf<ArticleItemBean>().apply {
                    addAll(homeArticles.data().datas)
                }
                refreshComplete()
            },
            error = {

            }

        )
    }

    fun getBanner() {
        launch(
            block = {
                val homeBanner = Api.mApiServer.getHomeBanner().data()
                bannerList.value = mutableListOf<BannerItemBean>().apply {
                    addAll(homeBanner)
                }
            }
        )
    }

    fun loadHomeArticle() {
        page++;
        launch(
            block = {
                val homeArticles = Api.mApiServer.getHomeArticles(page).data().datas
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