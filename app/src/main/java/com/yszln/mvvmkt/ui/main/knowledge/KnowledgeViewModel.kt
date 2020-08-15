package com.yszln.mvvmkt.ui.main.knowledge

import androidx.lifecycle.MutableLiveData
import com.yszln.lib.viewmodel.RefreshViewModel
import com.yszln.mvvmkt.api.Api

class KnowledgeViewModel : RefreshViewModel() {


    val treeList = MutableLiveData<MutableList<KnowLedgeItemBean>>()

   open fun getTree() {
        launch(
            block = {
                val data = Api.mApiServer.getKnowledgeTree().data()
                treeList.value = mutableListOf<KnowLedgeItemBean>().apply {
                    addAll(data)
                }
            }
        )
    }
}
