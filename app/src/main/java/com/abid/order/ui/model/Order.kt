package com.abid.order.ui.model

data class Order(var id: String, var store: String = "", var address: Address = Address());

data class Address(
    var streetName: String = "",
    var streetNumber: Int = -1,
    var postCode: String = ""
)