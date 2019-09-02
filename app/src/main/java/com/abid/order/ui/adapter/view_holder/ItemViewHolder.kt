package com.abid.order.ui.adapter.view_holder

import android.view.View
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.ui.model.MenuItem
import kotlinx.android.synthetic.main.menu_item.view.*

class ItemViewHolder(itemView: View) : BaseViewHolder(itemView) {
    val tvMenuTitle = itemView.tvTitle
    val tvMenuDetails = itemView.tvDetails
    val btnAdd = itemView.btnAdd
    override fun bindDate(data: MenuItem, itemClick: MenuItemClickListeners) {
        tvMenuDetails.text = data.description
        tvMenuTitle.text = data.title
        btnAdd.text = data.price.toString()
        btnAdd.setOnClickListener { itemClick.onClick(position = adapterPosition, item = data) }
    }

}