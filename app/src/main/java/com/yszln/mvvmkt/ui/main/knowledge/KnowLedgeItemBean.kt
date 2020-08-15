package com.yszln.mvvmkt.ui.main.knowledge

data class KnowLedgeItemBean(
    val children: MutableList<KnowLedgeItemBean>,
    val courseId: Int,
    val id: Int,
    var name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)