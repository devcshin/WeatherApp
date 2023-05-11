package com.devc.fitpettest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devc.fitpettest.data.model.ViewData
import com.devc.fitpettest.databinding.ForecastItemBinding
import com.devc.fitpettest.databinding.TitleItemBinding

class ForecastAdapter(data: List<ViewData>) : ListAdapter<ViewData, RecyclerView.ViewHolder>(DiffUtil()) {

    private val TITLE = 0
    private val ITEM = 1
    private val dataList = data

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].type == "Title") {
            TITLE
        } else {
            ITEM
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return if (viewType == TITLE) {
            TitleViewHolder(
                TitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            ForecastViewHolder(
                ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM){
            (holder as ForecastViewHolder).bind(dataList[position])
        }else{
            (holder as TitleViewHolder).bind(dataList[position])
        }
    }
}