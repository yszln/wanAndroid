package com.yszln.mvvmkt.ui.main.home.vm

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.utils.LogUtil
import com.yszln.lib.viewmodel.LoadMoreViewModel
import com.yszln.mvvmkt.api.Api
import com.yszln.mvvmkt.ui.article.ArticleItemBean

class HomeArticleViewModel : LoadMoreViewModel() {

    var type = 0;
    var loadMore = 1;


    val articleList = MutableLiveData<MutableList<ArticleItemBean>>()


    fun refreshHomeArticle(keyWord: String = "", cateId: Int = 0) {
        page = 1
        launch(
            block = {
                val homeArticles = getArticle(keyWord, cateId)
                articleList.value = mutableListOf<ArticleItemBean>().apply {
                    addAll(homeArticles)
                }
                refreshComplete()
            },
            error = {

            }

        )
    }

    private suspend fun getArticle(keyWord: String, cateId: Int): List<ArticleItemBean> {
        when (type) {
            0 -> {
                //首页
                return Api.mApiServer.getHomeArticles(page).data().datas;
            }
            1 -> {
                //置顶
                return Api.mApiServer.getTopArticles().data();
            }
            2 -> {
                //搜索
                LogUtil.e("搜索的关键字-》${keyWord}")
                return Api.mApiServer.search(page, keyWord).data().datas
            }
            3 -> {
                //知识体系文章
                return Api.mApiServer.getCateArticle(
                    page,
                    if (cateId == 0) null else cateId.toString()
                ).data().datas
            }
            4 -> {
                //项目
                return Api.mApiServer.getProject(page,cateId).data().datas
            }
            5->{
                //自己的文章
                return Api.mApiServer.myShareArticle(page).data().shareArticles.datas
            }
            6->{
                //收藏的文章
                return Api.mApiServer.getCollectArticle(page).data().datas
            }
            else -> {
                //首页
                return Api.mApiServer.getHomeArticles(page).data().datas;
            }
        }

    }


    fun loadHomeArticle(keyWord: String = "", cateId: Int = 0) {
        page++;
        launch(
            block = {
                val homeArticles = getArticle(keyWord, cateId)
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