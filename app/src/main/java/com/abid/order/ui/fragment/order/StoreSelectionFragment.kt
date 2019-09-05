package com.abid.order.ui.fragment.order


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abid.order.Constants
import com.abid.order.R
import com.abid.order.custom_view.StoreSelectionDialog
import com.abid.order.listeners.DialogListener
import com.abid.order.repository.Repository
import com.abid.order.ui.model.Address
import com.abid.order.utils.Utilities
import kotlinx.android.synthetic.main.fragment_store_selection.*


/**
 * A simple [Fragment] subclass.
 */
class StoreSelectionFragment : Fragment() {
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


        btnOrder.setOnClickListener {
            val dialog = StoreSelectionDialog(context, object : DialogListener {
                override fun onConfirm(address: Address) {
                }

                override fun onConfirm(id: Int) {
                    var order = Repository.instance.getCurrentOrder()
                    if (order == null) {
                        order = Repository.instance.createNewOrder()
                    }
                    Repository.realm.executeTransaction {
                        order?.store =
                            if (id == 0) Constants.STORE_A else Constants.STORE_B
                    }
                    findNavController().navigate(com.abid.order.R.id.action_storeSelectionFragment_to_locationSelectionFragment)
                }
            })
            dialog.show()
            val window = dialog.getWindow()
            window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, 1000);

        }

    }


}
