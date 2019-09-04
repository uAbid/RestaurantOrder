package com.abid.order.ui.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

enum class ViewType {
    HEADER, MENU_ITEM
}

open class MenuItem(
    @PrimaryKey
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var price: Float = 0F,
    var type: String = ""
) : RealmObject() {
    companion object {
        val ID = "id"

    }
}