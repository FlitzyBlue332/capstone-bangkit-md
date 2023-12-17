package com.capstone.sinari.view.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.sinari.databinding.ItemListTopicBinding

class NewsAdapter(private val newsList: List<NewsItem>, private val onItemClick: (NewsItem) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemListTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { onItemClick(currentItem) }
    }

    override fun getItemCount() = newsList.size

    class NewsViewHolder(private val binding: ItemListTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem) {
            binding.title.text = newsItem.title
            binding.location.text = newsItem.location
            binding.source.text = newsItem.source
            Glide.with(binding.root.context).load(newsItem.imageResource).into(binding.imageNews)
        }
    }
}