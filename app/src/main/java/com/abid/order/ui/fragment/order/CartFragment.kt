package com.abid.order.ui.fragment.order


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.abid.order.R
import com.abid.order.repository.Repository
import com.abid.order.ui.adapter.CartAdapter
import com.abid.order.ui.model.Item
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.custom_action_bar.*
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {
    lateinit var cartAdapter: CartAdapter
    var items: RealmResults<Item>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val order = Repository.instance.getCurrentOrder()
        if (order != null) {
            cartAdapter = CartAdapter(order)
            rcvCartList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = cartAdapter
            }
        }
        items = order?.items?.where()?.findAll()
        setSummaryData(items!!)
        items?.addChangeListener(RealmChangeListener() {
            setSummaryData(it)
        })

    }

    private fun setSummaryData(it: RealmResults<Item>) {
        var totalPrice = 0F
        for (item in it) {
            var price = 0F;
            if (item.menuItem != null) {
                price = item.menuItem!!.price
            }
            totalPrice += item.count * price
        }
        tvOrderTotal.text = "$totalPrice"
        tvGrandTotal.text = "${totalPrice +(totalPrice * 0.15)}"
    }

    override fun onDestroy() {
        super.onDestroy()
        items?.removeAllChangeListeners()
    }
}

