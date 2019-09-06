package com.abid.order.ui.adapter.view_holder

import android.view.View
import android.widget.RelativeLayout
import com.abid.order.custom_view.OrderDialog
import com.abid.order.listeners.DialogListener
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.ui.model.Address
import com.abid.order.ui.model.MenuItem
import kotlinx.android.synthetic.main.menu_item.view.*

class ItemViewHolder(itemView: View) : BaseViewHolder(itemView) {
    val tvMenuTitle = itemView.tvTitle
    val tvMenuDetails = itemView.tvDetails
    val btnAdd = itemView.btnAdd
    override fun bindDate(data: MenuItem, itemClick: MenuItemClickListeners) {
        tvMenuDetails.text = data.description
        tvMenuTitle.text = data.title
        btnAdd.text = data.price.toString()
        btnAdd.setOnClickListener {

            val order = OrderDialog(
                context = itemView.context,
                item = data,
                listener = object : DialogListener {
                    override fun onConfirm(id: Int) {
                    }

                    override fun onConfirm(address: Address) {
                    }

                    override fun onConfirm(note: String) {
                        itemClick.onClick(position = adapterPosition, item = data, note = note)
                    }

                })

            order.show()
            val window = order.getWindow()
            window.setLayout(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            );

        }
    }

}