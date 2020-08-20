package com.yszln.mvvmkt.ui.common

import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.webkit.*
import androidx.annotation.RequiresApi
import com.yszln.lib.BaseApplication
import com.yszln.lib.activity.BaseActivity
import com.yszln.lib.utils.LogUtil
import com.yszln.mvvmkt.R
import kotlinx.android.synthetic.main.activity_web.*

/**
 * @author: yszln
 * @date: 2020/8/10 10:11
 * @description: 公用的web页面
 * @history:
 */
class CommonWebActivity : BaseActivity(), DownloadListener {

    companion object {
        public fun start(url: String) {
            val intent = Intent(BaseApplication.mContext, CommonWebActivity::class.java)
            intent.putExtra("url", url)
            BaseApplication.mContext.startActivity(intent)
        }
    }

    private var mUrl: String? = null

    private val mHandler = Handler(Looper.getMainLooper())


    override fun layoutId() = R.layout.activity_web


    override fun initView() {

        initWebView()

        initData()

        initRefresh()

    }

    private fun initWebView() {
        webView.webChromeClient = MyChromeClient()
        webView.webViewClient = MyWebViewClient()
        webView.setDownloadListener(this)
    }

    private fun initRefresh() {
        mRefreshLayout.setOnRefreshListener {
            mUrl?.let { webView.loadUrl(it) }
            mHandler.postDelayed({
                mRefreshLayout.isRefreshing = false
            }, 300)

        }
    }

    private fun initData() {
        mUrl = intent?.getStringExtra("url")?.apply {
            webView.loadUrl(this)
        }
    }


    inner class MyChromeClient : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            titleBar.setTitle(title)
        }


    }

    inner class MyWebViewClient : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            request?.url?.run {
                return interceptUrl(this.toString())
            }
            return super.shouldOverrideUrlLoading(webView, request)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            url?.run {
                return interceptUrl(this.toString())
            }
            return super.shouldOverrideUrlLoading(view, url)
        }

    }

    private fun interceptUrl(url: String): Boolean {
        url.run {
            return when {
                contains("http") -> {
                    false
                }
                contains("jianshu") -> {
                    //唤醒简书
                    return true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onDownloadStart(
        url: String,
        userAgent: String,
        contentDisposition: String,
        mimetype: String,
        contentLength: Long
    ) {
        val fileName =
            URLUtil.guessFileName(url, contentDisposition, mimetype)
        LogUtil.e("onDownloadStart:fileName:${fileName},url${url}")
    }
}
