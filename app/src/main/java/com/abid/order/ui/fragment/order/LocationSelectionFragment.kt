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
import com.abid.order.ui.model.Address
import com.abid.order.utils.Utilities
import kotlinx.android.synthetic.main.fragment_location_selection.*

/**
 * A simple [Fragment] subclass.
 */
class LocationSelectionFragment : Fragment() {

    private var selectedMethod: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_selection, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvDelivery.setOnClickListener {
            setSelectedDelivery()
        }

        btnContinue.setOnClickListener {
            var order = Repository.instance.getCurrentOrder()
            Repository.realm.executeTransaction {
                order?.deliveryType =
                    if (selectedMethod == 0) Constants.DELIVERY else Constants.PICK_UP
                var address = order?.address
                if (address == null) {
                    address = it.createObject(Address::class.java, Utilities.getUniqueId())
                }
                address?.streetName = etStreet.text.toString().trim()
                address?.streetNumber = etStreetNumber.text.toString().trim()
                address?.city = etCity.text.toString().trim()
                address?.postCode = etPostalCode.text.toString().trim()
                order?.address = address;
            }
            findNavController().navigate(R.id.action_locationSelectionFragment_to_menuFragment)
        }
        tvPickup.setOnClickListener { setSelectedPickup() }
    }


    private fun initData() {
        val order = Repository.instance.getCurrentOrder()
        if (order?.deliveryType.equals(Constants.PICK_UP)) setSelectedPickup() else setSelectedDelivery()
        etStreet.setText(order?.address?.streetName)
        etStreetNumber.setText(order?.address?.streetNumber)
        etCity.setText(order?.address?.city)
        etPostalCode.setText(order?.address?.postCode)
    }

    override fun onResume() {
        super.onResume()
        initData()

    }

    private fun setSelectedPickup() {
        tvDelivery.background = ContextCompat.getDrawable(context!!, R.drawable.bg_unselected)
        tvPickup.background = ContextCompat.getDrawable(context!!, R.drawable.bg_selected)
        tvDelivery.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
        tvPickup.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        selectedMethod = 1
    }

    private fun setSelectedDelivery() {
        selectedMethod = 0
        tvDelivery.background = ContextCompat.getDrawable(context!!, R.drawable.bg_selected)
        tvPickup.background = ContextCompat.getDrawable(context!!, R.drawable.bg_unselected)
        tvDelivery.setTextColor(ContextCompat.getColor(context!!, R.color.colorBlack))
        tvPickup.setTextColor(ContextCompat.getColor(context!!, R.color.colorWhite))
    }
}
