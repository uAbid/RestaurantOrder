package com.abid.order.repository

import com.abid.order.ui.model.Order
import com.abid.order.utils.Utilities
import io.realm.Realm
import io.realm.Sort

class Repository {
    companion object {
        val instance = Repository()
        var realm: Realm

        init {
            realm = Realm.getDefaultInstance()
        }

        fun getDefaultRealm(): Realm {
            if (realm.isClosed) {
                realm = Realm.getDefaultInstance()
            }
            return realm;
        }
    }

    fun getCurrentOrder(): Order? {
        val orders = realm.where(Order::class.java).findAll().sort(
            Order.LAST_UPDATE,
            Sort.DESCENDING
        )
        if (orders != null && orders.size > 0) {
            return orders.first()
        } else {
            return null
        }
    }

    fun createNewOrder(): Order? {
        var order: Order
        realm.executeTransaction {
            realm.apply {
                order = this.createObject(Order::class.java, Utilities.getUniqueId())
                order.lastUpdate = System.currentTimeMillis()
            }
        }
        return getCurrentOrder()
    }
}