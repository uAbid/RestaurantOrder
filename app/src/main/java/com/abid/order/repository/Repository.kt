package com.abid.order.repository

import com.abid.order.ui.model.Order
import com.abid.order.utils.Utilities

class Repository {
    companion object {
        val instance = Repository()
    }

    fun getCurrentOrder(): Order {
        return Order(id = Utilities.getUniqueId())
    }
}