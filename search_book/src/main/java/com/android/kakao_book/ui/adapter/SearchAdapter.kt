package com.android.kakao_book.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.kakao_book.R
import com.android.kakao_book.databinding.ItemSearchBinding
import com.android.kakao_book.network.response.BookResult
import com.android.kakao_book.room.bookmark.BookmarkEntity

class SearchAdapter(private val onItemClick: (BookResult) -> Unit) :
    RecyclerView.Adapter<SearchViewHolder>() {
    private val bookList = mutableListOf<BookResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int =
        bookList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(bookList[position], onItemClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<BookResult>) {
        bookList.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        bookList.clear()
        notifyDataSetChanged()
    }

    fun toggleBookmark(item: BookResult) {
        bookList.firstOrNull { it.isbn == item.isbn }?.let {
            val index = bookList.indexOf(it)
            bookList[index] = item.copy(isBookmarked = !item.isBookmarked)
            notifyItemChanged(index)
        }
    }

    fun deleteBookmark(item: BookmarkEntity) {
        bookList.firstOrNull { it.isbn == item.isbn }?.let {
            val index = bookList.indexOf(it)
            bookList[index] = bookList[index].copy(isBookmarked = false)
            notifyItemChanged(index)
        }
    }
}

class SearchViewHolder(private val binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookResult, onItemClick: (BookResult) -> Unit) {
        binding.item = item

        val btnState = if (item.isBookmarked) R.drawable.like_red else R.drawable.like
        binding.btnBookmark.setBackgroundResource(btnState)

        binding.btnBookmark.setOnClickListener { onItemClick(item) }
    }
}