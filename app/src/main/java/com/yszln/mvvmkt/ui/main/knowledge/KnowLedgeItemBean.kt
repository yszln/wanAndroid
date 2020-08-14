package com.yszln.mvvmkt.ui.main.knowledge

data class KnowLedgeItemBean(
    val children: List<KnowLedgeItemBean>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)