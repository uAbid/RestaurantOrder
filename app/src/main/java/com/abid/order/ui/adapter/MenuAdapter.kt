package com.abid.order.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abid.order.R
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.ui.adapter.view_holder.BaseViewHolder
import com.abid.order.ui.adapter.view_holder.HeaderViewHolder
import com.abid.order.ui.adapter.view_holder.ItemViewHolder
import com.abid.order.ui.model.MenuItem
import com.abid.order.ui.model.ViewType

class MenuAdapter(var menus: ArrayList<MenuItem>, var listener: MenuItemClickListeners) :
    RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder {
        val item = menus[position]
        val viewHolder = when (item.type) {
            ViewType.HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.header_item,
                    parent,
                    false
                )
            )
            ViewType.MENU_ITEM -> ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.menu_item,
                    parent,
                    false
                )
            )
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun getItemViewType(position: Int): Int {
        return position;
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindDate(menus[position], listener)
    }
}