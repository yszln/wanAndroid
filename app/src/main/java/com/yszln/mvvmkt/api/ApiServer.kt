package com.yszln.mvvmkt.api

import com.yszln.lib.bean.BaseBean
import com.yszln.mvvmkt.ui.article.ArticleItemBean
import com.yszln.mvvmkt.ui.main.discover.bean.DiscoverBean
import com.yszln.mvvmkt.ui.main.home.bean.BannerItemBean
import com.yszln.mvvmkt.ui.main.knowledge.KnowLedgeItemBean
import com.yszln.mvvmkt.ui.main.mine.bean.IntegralBean
import com.yszln.mvvmkt.ui.main.mine.bean.MyArticleBean
import com.yszln.mvvmkt.ui.main.mine.bean.UserInfo
import com.yszln.mvvmkt.widget.cate.CateItemBean
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
     * 项目分类
     */
    @GET("/project/tree/json")
    suspend fun getProjectTree(): BaseBean<List<CateItemBean>>

    /**
     * 项目文章列表
     */
    @GET("/project/list/{page}/json")
    suspend fun getProject(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): BaseBean<PageBean<ArticleItemBean>>

    /**
     * 按照在实体性分类获取文章
     */
    @GET("/article/list/{page}/json")
    suspend fun getCateArticle(
        @Path("page") page: Int,
        @Query("cid") cid: String?
    ): BaseBean<PageBean<ArticleItemBean>>

    /**
     * 置顶文章
     */
    @GET("/article/top/json")
    suspend fun getTopArticles(): BaseBean<List<ArticleItemBean>>

    /**
     * 广场
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getUserArticle(@Path("page")page: Int):BaseBean<PageBean<ArticleItemBean>>

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
     * 注册
     */
    @POST("/user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): BaseBean<UserInfo>
    /**
     * 退出登录
     */
    @GET("/user/logout/json")
    suspend fun loginOut(): BaseBean<Any>

    /**
     * 自己分享的文章
     */
    @GET("/user/lg/private_articles/{page}/json")
    suspend fun myShareArticle(@Path("page") page: Int): BaseBean<MyArticleBean>

    /**
     * 收藏的文章
     */
    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticle(@Path("page") page: Int): BaseBean<PageBean<ArticleItemBean>>

    /**
     * 知识体系分类
     */
    @GET("tree/json")
    suspend fun getKnowledgeTree(): BaseBean<List<KnowLedgeItemBean>>

    /**
     * 常用网站
     */
    @GET("/friend/json")
    suspend fun frequentlyUsedWebsites(): BaseBean<List<DiscoverBean>>

    /**
     * 我的积分
     */
    @GET("/lg/coin/userinfo/json")
    suspend fun getUserIntegral(): BaseBean<IntegralBean>

    /**
     * 积分记录
     */
    @GET("user/{userId}/articles/{page}")
    suspend fun getUserIntegralInfo(@Path("userId") userId: Int, @Path("page") page: Int):BaseBean<PageBean<IntegralBean>>

    /**
     * 积分排行榜
     */
    @GET("/coin/rank/{page}/json")
    suspend fun getUserIntegralRank(@Path("page")page:Int)


}