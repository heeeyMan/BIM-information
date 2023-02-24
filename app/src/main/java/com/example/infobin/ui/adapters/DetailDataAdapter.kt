package com.example.infobin.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infobin.databinding.HistoryItemBinding
import com.example.infobin.datamodels.HistoryItemData

class DetailDataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: MutableList<HistoryItemData> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val itemView = HistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val itemViewHolder = holder as HomeViewHolder
        val currentUser = dataList[position]
        itemViewHolder.bind(currentUser)
    }

    override fun getItemCount() = dataList.size

    inner class HomeViewHolder(
        private val binding: HistoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryItemData) {
            binding.header.text = item.request
            binding.value.text = item.data
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMoreItems(items: List<HistoryItemData>) {
        dataList.clear()
        dataList.addAll(items)
        notifyDataSetChanged()
    }
}