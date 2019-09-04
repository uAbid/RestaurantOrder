package com.abid.order.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.abid.order.R
import com.abid.order.repository.Repository
import com.abid.order.ui.adapter.CartAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {
    lateinit var cartAdapter: CartAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var order = Repository.instance.getCurrentOrder()
        cartAdapter = CartAdapter(order)

        rcvCartList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapter
        }
    }
}
