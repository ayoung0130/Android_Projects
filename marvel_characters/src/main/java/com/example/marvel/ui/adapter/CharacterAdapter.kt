package com.example.marvel.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.databinding.ItemCharacterBinding
import com.example.marvel.network.response.CharacterResult
import com.example.marvel.room.BookmarkEntity

class CharacterAdapter(private val onItemClick: (CharacterResult) -> Unit) :
    RecyclerView.Adapter<CharacterViewHolder>() {
    private val characterList = mutableListOf<CharacterResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position], onItemClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<CharacterResult>) {
        characterList.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        characterList.clear()
        notifyDataSetChanged()
    }

    fun toggleBookmark(item: BookmarkEntity) {
        characterList.firstOrNull { it.id == item.id }?.let { item ->
            val i = characterList.indexOf(item)
            characterList[i] = item.copy(isBookmarked = !item.isBookmarked)
            notifyItemChanged(i)
        }
    }
}


class CharacterViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: CharacterResult,
        onItemClick: (CharacterResult) -> Unit
    ) {
        binding.item = item
        val btnBookmarkState =
            if (item.isBookmarked) R.drawable.baseline_star_24 else R.drawable.baseline_star_outline_24
        binding.btnBookmark.setBackgroundResource(btnBookmarkState)
        binding.btnBookmark.setOnClickListener { onItemClick(item) }
    }
}