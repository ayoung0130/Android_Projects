package com.example.marvel.ext

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.marvel.exception.NetworkFailureException
import java.io.OutputStream

fun saveImageToLocal(imageUrl: String, context: Context) {

    Glide.with(context)
        .asDrawable()
        .load(imageUrl)
        .into(object : CustomTarget<Drawable>() {
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                val fileName = "marvel_character"
                val path = "${Environment.DIRECTORY_PICTURES}/Marvel Character"

                val contentUri =
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "$fileName.jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(MediaStore.Images.Media.RELATIVE_PATH, path)
                }

                val imageUrl = context.contentResolver.insert(contentUri, contentValues)

                try {
                    val outputStream: OutputStream? =
                        context.contentResolver.openOutputStream(imageUrl!!)
                    outputStream?.let {
                        resource.toBitmap().compress(Bitmap.CompressFormat.JPEG, 100, it)
                        it.close()
                    }

                    // 갤러리에 이미지 추가
                    MediaScannerConnection.scanFile(
                        context,
                        arrayOf(imageUrl.path),
                        arrayOf("image/jpeg"),
                        null
                    )
                    Toast.makeText(context, "이미지가 저장되었습니다", Toast.LENGTH_SHORT).show()
                } catch (e: NetworkFailureException) {
                    e.message
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
}