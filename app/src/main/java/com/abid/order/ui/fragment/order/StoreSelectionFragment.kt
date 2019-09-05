package com.abid.order.ui.fragment.order


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abid.order.Constants
import com.abid.order.R
import com.abid.order.repository.Repository
import com.abid.order.utils.Utilities
import kotlinx.android.synthetic.main.fragment_store_selection.*


/**
 * A simple [Fragment] subclass.
 */
class StoreSelectionFragment : Fragment() {
    var selectedStore: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_selection, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Utilities.setTitle(activity = activity, title = "Store Selection")

        tvStoreB.setOnClickListener {
            setSelectedStoreB()
        }
        tvStoreA.setOnClickListener {
            setSelectedStoreA()
        }

        btnOrder.setOnClickListener {
            var order = Repository.instance.getCurrentOrder()
            if (order == null) {
                order = Repository.instance.createNewOrder()
            }
            Repository.realm.executeTransaction {
                order?.store= if (selectedStore==0) Constants.STORE_A else Constants.STORE_B
            }
            findNavController().navigate(R.id.action_storeSelectionFragment_to_locationSelectionFragment)
        }

    }


    private fun initData() {
        val order = Repository.instance.getCurrentOrder()
        if (order?.store.equals(Constants.STORE_A)) {
            setSelectedStoreA()
        } else if (order?.store.equals(Constants.STORE_B)) {
            setSelectedStoreB()
        }

    }

    override fun onResume() {
        super.onResume()
        if (selectedStore == 0) {
            setSelectedStoreA()
        } else {
            setSelectedStoreB()
        }
        initData()
    }

    private fun setSelectedStoreB() {
        tvStoreA.background = ContextCompat.getDrawable(context!!, R.drawable.bg_unselected)
        tvStoreB.background = ContextCompat.getDrawable(context!!, R.drawable.bg_selected)
        tvStoreA.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
        tvStoreB.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        selectedStore = 1
    }

    private fun setSelectedStoreA() {
        selectedStore = 0
        tvStoreA.background = ContextCompat.getDrawable(context!!, R.drawable.bg_selected)
        tvStoreB.background = ContextCompat.getDrawable(context!!, R.drawable.bg_unselected)
        tvStoreA.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        tvStoreB.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
    }

}
