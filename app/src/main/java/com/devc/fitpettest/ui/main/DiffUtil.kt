package com.devc.fitpettest.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.devc.fitpettest.data.model.ViewData

class DiffUtil : DiffUtil.ItemCallback<ViewData>() {

    override fun areItemsTheSame(oldItem: ViewData, newItem: ViewData) =
        oldItem === newItem

    override fun areContentsTheSame(oldItem: ViewData, newItem: ViewData) =
        oldItem == newItem
}