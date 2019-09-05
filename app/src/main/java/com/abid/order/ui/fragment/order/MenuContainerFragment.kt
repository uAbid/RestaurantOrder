package com.abid.order.ui.fragment.order


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abid.order.R
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.repository.Repository
import com.abid.order.ui.adapter.MenuPagerAdapter
import com.abid.order.ui.model.Item
import com.abid.order.ui.model.Menu
import com.abid.order.ui.model.MenuItem
import com.abid.order.ui.model.Order
import com.abid.order.utils.Utilities
import kotlinx.android.synthetic.main.fragment_menu_container.*

class MenuContainerFragment : Fragment(), MenuItemClickListeners {

    var order: Order? = null
    override fun onClick(position: Int, item: MenuItem) {
        Repository.realm.executeTransaction {
            var itemObject = order?.items?.where()?.equalTo(Item.ID, item.id)?.findFirst()
            if (itemObject != null) {
                itemObject.count++;
            } else {
                itemObject = it.createObject(Item::class.java, item.id)
                itemObject?.count = 1;
                order?.items?.add(itemObject)
            }
            var menuItem =
                Repository.realm.where(MenuItem::class.java).equalTo(MenuItem.ID, item.id)
                    .findFirst()
            if (menuItem == null) {
                menuItem = it.copyToRealm(item)
            }
            itemObject?.menuItem = menuItem
        }
        updateCart()
    }

    private fun updateCart() {
        if (order?.items != null && order?.items!!.size > 0) {
            cartCountContainer.visibility = View.VISIBLE
            var count = 0;
            for (item in order?.items!!) {
                count += item.count
            }
            tvCartCount.text = count.toString()
        } else {
            cartCountContainer.visibility = View.GONE
        }
    }

    lateinit var pagerAdpter: MenuPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Utilities.setTitle(activity,"Menu")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_container, container, false)
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        order = Repository.instance.getCurrentOrder()

        val menus = arrayListOf(
            Menu(Utilities.getUniqueId(), "Chinease",
                MenuFragment()
            ),
            Menu(Utilities.getUniqueId(), "Indian", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Thai", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Italian",
                MenuFragment()
            ),
            Menu(Utilities.getUniqueId(), "French", MenuFragment()),
            Menu(Utilities.getUniqueId(), "Turkish",
                MenuFragment()
            ),
            Menu(Utilities.getUniqueId(), "Maxican",
                MenuFragment()
            )
        )
        pagerAdpter = MenuPagerAdapter(menus, childFragmentManager)
        menuPager.adapter = pagerAdpter
        tbMenuTab.setupWithViewPager(menuPager)
        for (menu in menus) {
            menu.fragment.listener = this
        }
        fabCart.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_cartFragment) }

        updateCart()
    }
}
