package com.yszln.mvvmkt.ui.main.home.vm

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.bean.BaseBean
import com.yszln.lib.viewmodel.LoadMoreViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.api.PageBean
import com.yszln.mvvmkt.ui.article.ArticleItemBean

class HomeArticleViewModel : LoadMoreViewModel() {

    var type = 0;
    var loadMore = 1;



    val articleList = MutableLiveData<MutableList<ArticleItemBean>>()


    fun refreshHomeArticle() {
        page = 1
        launch(
            block = {
                val homeArticles = getArticle()
                articleList.value = mutableListOf<ArticleItemBean>().apply {
                    addAll(homeArticles)
                }
                refreshComplete()
            },
            error = {

            }

        )
    }

    private suspend fun getArticle(): List<ArticleItemBean> {
        when (type) {
            0 -> {
                //首页
                return Api.mApiServer.getHomeArticles(page).data().datas;
            }
            1 -> {
                //置顶
                return Api.mApiServer.getTopArticles().data();
            }
            else -> {
                //首页
                return Api.mApiServer.getHomeArticles(page).data().datas;
            }
        }

    }


    fun loadHomeArticle() {
        page++;
        launch(
            block = {
                val homeArticles = getArticle()
                articleList.value?.apply {
                    addAll(homeArticles)
                }
                loadMoreComplete()
            },
            error = {

            }

        )
    }


}