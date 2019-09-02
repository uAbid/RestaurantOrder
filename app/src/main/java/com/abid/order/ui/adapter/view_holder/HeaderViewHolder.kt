package com.abid.order.ui.adapter.view_holder

import android.view.View
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.ui.model.MenuItem
import kotlinx.android.synthetic.main.header_item.view.*

class HeaderViewHolder(itemView: View) : BaseViewHolder(itemView) {
    val headerTitle = itemView.tvHeaderTitle
    override fun bindDate(data: MenuItem, itemClick: MenuItemClickListeners) {
        headerTitle.text = data.title
    }

}