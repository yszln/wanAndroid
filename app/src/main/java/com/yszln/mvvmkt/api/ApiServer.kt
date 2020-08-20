package com.yszln.mvvmkt.api

import com.yszln.lib.bean.BaseBean
import com.yszln.mvvmkt.ui.article.ArticleItemBean
import com.yszln.mvvmkt.ui.main.discover.bean.DiscoverBean
import com.yszln.mvvmkt.ui.main.home.bean.BannerItemBean
import com.yszln.mvvmkt.ui.main.knowledge.KnowLedgeItemBean
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
     * 按照在实体性分类获取文章
     */
    @GET("/article/list/{page}/json")
    suspend fun getCateArticle(@Path("page") page: Int,@Query("cid") cid: String?): BaseBean<PageBean<ArticleItemBean>>

    /**
     * 置顶文章
     */
    @GET("/article/top/json")
    suspend fun getTopArticles(): BaseBean<List<ArticleItemBean>>

    /**
     * 搜索
     * @param page 页码
     * @param keyWord 关键字
     */
    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    suspend fun search(
        @Path("page") page: Int,
        @Field("k") keyWord: String
    ): BaseBean<PageBean<ArticleItemBean>>

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

    /**
     * 知识体系分类
     */
    @GET("tree/json")
    suspend fun getKnowledgeTree(): BaseBean<List<KnowLedgeItemBean>>

    /**
     * 常用网站
     */
    @GET("/friend/json")
    suspend fun frequentlyUsedWebsites() :BaseBean<List<DiscoverBean>>
}