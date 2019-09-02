package com.abid.order.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.abid.order.R
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.ui.adapter.MenuPagerAdapter
import com.abid.order.ui.model.Menu
import com.abid.order.ui.model.MenuItem
import com.abid.order.utils.Utilities
import kotlinx.android.synthetic.main.fragment_menu_container.*

class MenuContainerFragment : Fragment(), MenuItemClickListeners {

    lateinit var cart: ArrayList<MenuItem>
    override fun onClick(position: Int, item: MenuItem) {
        Toast.makeText(context, "Position $position with item $item", Toast.LENGTH_SHORT).show()
        cart.add(item)
        updateCart()
    }

    private fun updateCart() {
        if (cart.size > 0) {
            cartCountContainer.visibility = View.VISIBLE
            tvCartCount.text = cart.size.toString()
        } else {
            cartCountContainer.visibility = View.GONE
        }
    }

    lateinit var pagerAdpter: MenuPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_container, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cart = arrayListOf()
        val menus = arrayListOf(
            Menu(Utilities.getUniqueId(), "Chinease", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Indian", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Thai", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Italian", MenuFragment()),
            Menu(Utilities.getUniqueId(), "French", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Turkish", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Maxican", MenuFragment())
        )
        pagerAdpter = MenuPagerAdapter(menus, childFragmentManager)
        menuPager.adapter = pagerAdpter
        tbMenuTab.setupWithViewPager(menuPager)
        for (menu in menus) {
            menu.fragment.listener = this
        }
    }
}
