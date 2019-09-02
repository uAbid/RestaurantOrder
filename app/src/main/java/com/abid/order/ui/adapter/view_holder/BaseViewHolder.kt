package com.abid.order.ui.adapter.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.ui.model.MenuItem

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindDate(data: MenuItem, itemClick: MenuItemClickListeners)
}