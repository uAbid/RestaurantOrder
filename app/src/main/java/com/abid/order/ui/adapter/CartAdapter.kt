package com.abid.order.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abid.order.R
import com.abid.order.repository.Repository
import com.abid.order.ui.model.Order
import kotlinx.android.synthetic.main.cart_item.view.*

class CartAdapter(var order: Order) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.tvTitle
        val tvSubtitle = view.tvSubtitle
        val tvCount = view.tvCount
        val tvTotalPrice = view.tvTotalPrice
        val ivPlus = view.ivPlus
        val ivMinus = view.ivMinus
        val ivDelete = view.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cart_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = order.items[position]
        var count = 0
        var price = 0F
        price = item?.menuItem?.price!!
        count = item.count

        holder.apply {
            tvCount.text = "${item.count}"
            tvTitle.text = item.menuItem?.title
            tvSubtitle.text = item.note
            tvTotalPrice.text = "${count * price}"
            ivPlus.setOnClickListener {
                Repository.realm.executeTransaction {
                    item.count++
                }
                notifyDataSetChanged()
            }
            ivMinus.setOnClickListener {
                Repository.realm.executeTransaction {
                    if (item.count - 1 > 0) {
                        item.count--
                    }
                }
                notifyDataSetChanged()
            }

            ivDelete.setOnClickListener {
                Repository.realm.executeTransaction {
                    order.items.remove(item)
                    item.deleteFromRealm()
                }
                notifyDataSetChanged()
            }
        }

    }

    override fun getItemCount(): Int {
        return order.items.size
    }
}