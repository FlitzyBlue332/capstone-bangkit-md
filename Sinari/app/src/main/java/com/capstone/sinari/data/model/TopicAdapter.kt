package com.capstone.sinari.data.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.sinari.R
import com.capstone.sinari.databinding.ItemListTopicBinding

class TopicAdapter(
    private val topicList: List<TopicItem>,
    private val onItemClick: (TopicItem) -> Unit
) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding =
            ItemListTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val currentItem = topicList[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { onItemClick(currentItem) }
    }

    override fun getItemCount() = topicList.size

    class TopicViewHolder(private val binding: ItemListTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(topicItem: TopicItem) {
            binding.title.text = topicItem.title
            binding.location.text = topicItem.location
            binding.source.text = topicItem.source
            Glide.with(binding.root.context).load(topicItem.imageUrl).into(binding.imageNews)
        }
    }
}