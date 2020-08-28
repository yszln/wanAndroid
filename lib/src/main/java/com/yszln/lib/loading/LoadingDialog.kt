package com.yszln.lib.loading

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.yszln.lib.R


class LoadingDialog(context: Context) : Dialog(context),
    ILoading {
    init {

        //去除半透明阴影
        val layoutParams: WindowManager.LayoutParams = window!!.attributes
        layoutParams.dimAmount = 0.0f
        window!!.attributes = layoutParams
        window?.setBackgroundDrawableResource(R.color.transparent);

        setContentView(R.layout.dialog_loading)
        setCanceledOnTouchOutside(false)

    }


    override fun showLoading() {
        show()
    }

    override fun dismissLoading() {
        dismiss()
    }
}