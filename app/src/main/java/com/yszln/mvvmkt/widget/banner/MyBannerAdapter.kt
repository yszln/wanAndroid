package com.yszln.mvvmkt.widget.banner

import android.content.Intent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.adapter.BannerAdapter
import com.yszln.mvvmkt.ui.common.CommonWebActivity
import com.yszln.mvvmkt.ui.main.home.bean.BannerItemBean


class MyBannerAdapter(datas: MutableList<BannerItemBean>) :
    BannerAdapter<BannerItemBean, MyBannerAdapter.BannerViewHolder>(datas) {
    class BannerViewHolder(view: ImageView) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent!!.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }


    override fun onBindView(
        holder: BannerViewHolder,
        data: BannerItemBean,
        position: Int,
        size: Int
    ) {
        //图片加载自己实现
        Glide.with(holder.itemView)
            .load(data.imagePath)
//            .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
            .into(holder.imageView);

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CommonWebActivity::class.java)
            intent.putExtra("url", getData(position).url)
            holder.itemView.context.startActivity(intent)
        }
    }
}