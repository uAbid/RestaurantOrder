package com.abid.order.ui.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Order(
    @PrimaryKey
    var id: String = "",
    var store: String = "",
    var deliveryType: String = "",
    var address: Address? = null,
    var items: RealmList<Item> = RealmList(),
    var lastUpdate: Long = 0,
    var complete: Boolean = false,
    var status: String = ""

) : RealmObject() {

    companion object {
        val LAST_UPDATE = "lastUpdate"
        val COMPLETE = "complete"
        val STATUS = "status"
        val ID = "id"

    }
}


open class Address(
    @PrimaryKey
    var id: String = "",
    var streetName: String = "",
    var streetNumber: String = "",
    var postCode: String = "",
    var city: String = ""
) : RealmObject()

open class Item(@PrimaryKey var id: String = "", var menuItem: MenuItem? = null, var count: Int = 0) :
    RealmObject(){
    companion object {
        val ID = "id"

    }
}