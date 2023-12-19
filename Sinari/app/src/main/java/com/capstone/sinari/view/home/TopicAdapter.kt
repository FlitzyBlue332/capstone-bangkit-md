package com.capstone.sinari.view.home

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.sinari.data.response.TopicItem
import com.capstone.sinari.databinding.ItemListTopicBinding
import com.capstone.sinari.view.detailnews.BiasActivity
import com.capstone.sinari.view.detailtopic.TopicActivity

class TopicAdapter: PagingDataAdapter<TopicItem, TopicAdapter.TopicViewHolder>(DIFF_CALLBACK) {

    class TopicViewHolder(val binding: ItemListTopicBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TopicItem>() {
            override fun areItemsTheSame(
                oldItem: TopicItem,
                newItem: TopicItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TopicItem,
                newItem: TopicItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int
    ): TopicViewHolder {
        val binding = ItemListTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val currentItem = getItem(position)
        val topic = currentItem!!.topic
        Log.d("check_item", "${topic.title}, ${topic.url}")

        holder.binding.apply {
            holder.binding.title.text = topic.title
            holder.binding.source.text = topic.publisherName
        }
        Glide.with(holder.itemView.context)
            .load(topic.thumbnail)
            .into(holder.binding.imageNews)

        // set item click
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, TopicActivity::class.java)
            intentDetail.putExtra(TopicActivity.EXTRA_TOPIC, currentItem)
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}