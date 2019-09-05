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
import com.abid.order.custom_view.AddressDialog
import com.abid.order.listeners.DialogListener
import com.abid.order.repository.Repository
import com.abid.order.ui.model.Address
import com.abid.order.utils.Utilities
import kotlinx.android.synthetic.main.fragment_location_selection.*

/**
 * A simple [Fragment] subclass.
 */
class LocationSelectionFragment : Fragment() {

    private var selectedMethod: Int = 0

    lateinit var addressList: ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_selection, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addressList = arrayListOf("", "", "", "")
        btnDelivery.setOnClickListener {
            selectedMethod = 0
            getAddress()
            setMethodWithAddress()
        }
        btnPickup.setOnClickListener {
            selectedMethod = 1
            getAddress()
            setMethodWithAddress()
        }
    }

    private fun getAddress() {
        val addressData = etAddress.text.trim()
        val parts = addressData.split(",")
        for (i in 0..parts.size - 1) {
            addressList.set(i, parts[i])
        }
    }


    private fun setMethodWithAddress() {
        var order = Repository.instance.getCurrentOrder()
        if (valid()) {
            Repository.realm.executeTransaction {
                order?.deliveryType =
                    if (selectedMethod == 0) Constants.DELIVERY else Constants.PICK_UP
                var address = order?.address
                if (address == null) {
                    address = it.createObject(Address::class.java, Utilities.getUniqueId())
                }

                address?.streetName = addressList[0]
                address?.streetNumber = addressList[1]
                address?.city = addressList[2]
                address?.postCode = addressList[3]
                order?.address = address;


            }
            findNavController().navigate(R.id.action_locationSelectionFragment_to_menuFragment)
        } else {
            val addressDialog = AddressDialog(context, addressList, object : DialogListener {
                override fun onConfirm(id: Int) {

                }

                override fun onConfirm(address: Address) {
                    Repository.realm.executeTransaction {
                        order?.deliveryType =
                            if (selectedMethod == 0) Constants.DELIVERY else Constants.PICK_UP
                        var addressRealm = order?.address
                        if (addressRealm == null) {
                            addressRealm =
                                it.createObject(Address::class.java, Utilities.getUniqueId())
                        }

                        addressRealm?.streetName = address.streetName
                        addressRealm?.streetNumber = address.streetNumber
                        addressRealm?.city = address.city
                        addressRealm?.postCode = address.postCode
                        order?.address = addressRealm;


                    }
                    findNavController().navigate(R.id.action_locationSelectionFragment_to_menuFragment)
                }

            })
            addressDialog.show()
            val window = addressDialog.getWindow()
            window.setLayout(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            );
        }

    }

    private fun valid(): Boolean {
        var valid = true
        for (item in addressList) {
            if (item.isNullOrEmpty()) {
                valid = false;
                break
            }
        }
        return valid
    }


    private fun initData() {
        val order = Repository.instance.getCurrentOrder()
        tvSeletedStore.text = getString(R.string.your_selected_store_is) + " ${order?.store}"
        var addresString = ""
        val address = order?.address
        if (address != null) {
            if (!address.streetName.isNullOrEmpty()) {
                addresString += address.streetName + ","
            }
            if (!address.streetNumber.isNullOrEmpty()) {
                addresString += address.streetNumber + ","
            }
            if (!address.city.isNullOrEmpty()) {
                addresString += address.city + ","
            }
            if (!address.postCode.isNullOrEmpty()) {
                addresString += address.postCode
            }
        }
        etAddress.setText(addresString)
    }

    override fun onResume() {
        super.onResume()
        initData()

    }
}
