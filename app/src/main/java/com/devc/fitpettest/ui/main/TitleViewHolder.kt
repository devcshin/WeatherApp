package com.devc.fitpettest.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.devc.fitpettest.data.model.ViewData
import com.devc.fitpettest.databinding.TitleItemBinding

class TitleViewHolder(private val binding: TitleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ViewData) {
        binding.tvTitle.text = data.region
    }
}