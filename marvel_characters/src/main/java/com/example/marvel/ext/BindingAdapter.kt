package com.example.marvel.ext

import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.marvel.network.response.MarvelThumbnail

@BindingAdapter("setUrlImg")
fun ImageView.setUrlImg(thumbnail: MarvelThumbnail?) {
    thumbnail?.let { marvelThumbnail ->
        val url = marvelThumbnail.getThumbnail()

        // 이미지의 URL 받아와 로드, 모서리 적용 후 ImageView에 표시
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(this)

        setOnClickListener {
            if (!url.endsWith("image_not_available.jpg")) {
                saveImageToLocal(url, this.context)
            } else {
                Toast.makeText(context, "이미지가 없어 저장할 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@BindingAdapter("onScrolledBottomLine")
fun RecyclerView.onScrolledBottomLine(f: Function1<Boolean, Unit>?) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            f?.invoke(!recyclerView.canScrollVertically(1))
        }
    })
}
@BindingAdapter("setRefreshState")
fun SwipeRefreshLayout.setRefreshState(isRefresh: Boolean) {
    isRefreshing = isRefresh
}