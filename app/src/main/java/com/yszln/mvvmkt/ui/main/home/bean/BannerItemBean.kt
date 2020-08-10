package com.yszln.mvvmkt.ui.main.home.bean

data class BannerItemBean(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)