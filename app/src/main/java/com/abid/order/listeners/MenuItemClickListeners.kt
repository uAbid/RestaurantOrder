package com.abid.order.listeners

import com.abid.order.ui.model.MenuItem


interface MenuItemClickListeners {
    fun onClick(position: Int, item: MenuItem)
}