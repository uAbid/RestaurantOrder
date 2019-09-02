package com.abid.order.ui.model

enum class ViewType {
    HEADER, MENU_ITEM
}

data class MenuItem(
    var id: String,
    var title: String,
    var description: String,
    var price: Float,
    var type: ViewType
)