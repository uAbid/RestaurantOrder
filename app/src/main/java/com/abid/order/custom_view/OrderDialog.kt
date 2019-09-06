package com.abid.order.custom_view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.abid.order.R
import com.abid.order.listeners.DialogListener
import com.abid.order.ui.model.MenuItem
import kotlinx.android.synthetic.main.btn_layout.*
import kotlinx.android.synthetic.main.layout_item_order.*

class OrderDialog(context: Context, var item: MenuItem, var listener: DialogListener) :
    Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_item_order)

        btnConfirm.text = context.getString(R.string.add_to_cart) +"  "+ item.price
        btnConfirm.setOnClickListener {
            listener.onConfirm(etNote.text.toString().trim())
            dismiss()
        }
        tvItemTitle.text = item.title

        ivCancel.setOnClickListener { dismiss() }
    }

}