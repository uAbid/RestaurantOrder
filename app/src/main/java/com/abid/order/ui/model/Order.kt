package com.abid.order.ui.model

data class Order(
    var id: String,
    var store: String = "",
    var address: Address = Address(),
    var items: ArrayList<Item> = arrayListOf()
);

data class Address(
    var streetName: String = "",
    var streetNumber: Int = -1,
    var postCode: String = ""
)

data class Item(var id: String, var menuItem: MenuItem, var count: Int)