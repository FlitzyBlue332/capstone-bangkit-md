package com.capstone.sinari.view.newslist

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.sinari.data.response.NewsItem
import com.capstone.sinari.data.response.TopicItem
import com.capstone.sinari.databinding.ItemListNewsBinding
import com.capstone.sinari.databinding.ItemListTopicBinding
import com.capstone.sinari.view.detailnews.BiasActivity
import com.capstone.sinari.view.detailtopic.TopicActivity
import com.capstone.sinari.view.home.TopicAdapter

class NewsAdapter: ListAdapter<NewsItem, NewsAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    class NewsViewHolder(val binding: ItemListNewsBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(
                oldItem: NewsItem,
                newItem: NewsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: NewsItem,
                newItem: NewsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            holder.binding.title.text = currentItem.title
            holder.binding.source.text = currentItem.publisherName
        }
        Glide.with(holder.itemView.context).apply {
            load(currentItem.publisherLogo).into(holder.binding.ivPublisher)
            load(currentItem.thumbnail).into(holder.binding.ivNewsList)
        }

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, BiasActivity::class.java)
            intentDetail.putExtra(BiasActivity.EXTRA_NEWS, currentItem)
            holder.itemView.context.startActivity(intentDetail)
        }
    }


}