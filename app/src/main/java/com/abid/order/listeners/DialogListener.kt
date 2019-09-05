package com.abid.order.listeners

import com.abid.order.ui.model.Address

interface DialogListener {
    fun onConfirm(id: Int)
    fun onConfirm(address: Address)

}