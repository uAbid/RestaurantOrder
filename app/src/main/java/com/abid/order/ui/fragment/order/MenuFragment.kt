package com.abid.order.ui.fragment.order


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.abid.order.R
import com.abid.order.listeners.MenuItemClickListeners
import com.abid.order.ui.adapter.MenuAdapter
import com.abid.order.ui.model.MenuItem
import com.abid.order.ui.model.ViewType
import com.abid.order.utils.Utilities
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {
    lateinit var listener:MenuItemClickListeners
    lateinit var menuAdapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var menuList = arrayListOf<MenuItem>()

        menuList.add(MenuItem(Utilities.getUniqueId(),"BreakFast","",0F,ViewType.HEADER.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Lunch","",0F,ViewType.HEADER.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Dinner","",0F,ViewType.HEADER.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))
        menuList.add(MenuItem(Utilities.getUniqueId(),"Fried Rice","afskja kas,asdsadasd,asdsadsadasd,adsdasdsad,asdasdasd",120F,ViewType.MENU_ITEM.name))

        menuAdapter = MenuAdapter(menuList, listener)

        rcvMenuList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = menuAdapter
        }
    }

}
