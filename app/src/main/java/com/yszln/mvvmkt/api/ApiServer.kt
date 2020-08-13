package com.yszln.mvvmkt.api

import com.yszln.lib.bean.BaseBean
import com.yszln.mvvmkt.ui.article.ArticleItemBean
import com.yszln.mvvmkt.ui.main.home.bean.BannerItemBean
import com.yszln.mvvmkt.ui.main.mine.bean.UserInfo
import retrofit2.http.*

/**
 * 用来注解类和方法，使得被标记元素的泛型参数不会被编译成通配符?
 */
@JvmSuppressWildcards
interface ApiServer {

    /**
     * 首页文章列表
     */
    @GET("article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BaseBean<PageBean<ArticleItemBean>>

    /**
     * 置顶文章
     */
    @GET("/article/top/json")
    suspend fun getTopArticles(): BaseBean<List<ArticleItemBean>>

    /**
     * 首页banner
     */
    @GET("/banner/json")
    suspend fun getHomeBanner(): BaseBean<List<BannerItemBean>>

    /**
     * 登陆
     */
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseBean<UserInfo>
}