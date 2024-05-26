package com.example.aroundhospital.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aroundhospital.databinding.ItemBookmarkBinding
import com.example.aroundhospital.room.BookmarkEntity
import com.example.aroundhospital.ui.dialog.LinkDialogFragment
import com.example.aroundhospital.ui.dialog.PhoneDialogFragment

class BookmarkAdapter(private val onClickEvent: (BookmarkClickEventType) -> Unit) :
    ListAdapter<BookmarkEntity, BookmarkViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position), onClickEvent)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BookmarkEntity>() {
            override fun areItemsTheSame(
                oldItem: BookmarkEntity,
                newItem: BookmarkEntity
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: BookmarkEntity,
                newItem: BookmarkEntity
            ): Boolean = oldItem.id == newItem.id
        }
    }
}


class BookmarkViewHolder(private val binding: ItemBookmarkBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: BookmarkEntity,
        onClickEvent: (BookmarkClickEventType) -> Unit
    ) {
        with(binding) {
            document = item
            btnPhone.setOnClickListener {
                onClickEvent(BookmarkClickEventType.ShowPhoneDialog(item))
            }
            btnLink.setOnClickListener {
                onClickEvent(BookmarkClickEventType.ShowLinkDialog(item))
            }
            btnBookmark.setOnClickListener {
                onClickEvent(BookmarkClickEventType.DeleteBookmark(item))
            }
            root.setOnClickListener {
                onClickEvent(BookmarkClickEventType.ClickItem(item))
            }
        }
    }
}

sealed interface BookmarkClickEventType {

    data class ClickItem(val item: BookmarkEntity) : BookmarkClickEventType

    data class DeleteBookmark(val item: BookmarkEntity) : BookmarkClickEventType

    data class ShowPhoneDialog(val item: BookmarkEntity) : BookmarkClickEventType

    data class ShowLinkDialog(val item: BookmarkEntity) : BookmarkClickEventType
}