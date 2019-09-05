package com.abid.order.custom_view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.abid.order.R
import com.abid.order.listeners.DialogListener
import com.abid.order.ui.model.Address
import kotlinx.android.synthetic.main.address_layout.*
import kotlinx.android.synthetic.main.btn_layout.*

class AddressDialog(
    context: Context?,
    var addressList: ArrayList<String>,
    var listener: DialogListener
) : Dialog(context) {
    val streetError = "Please provide a valid street address"
    val streetNumerError = "Please provide a valid street number"
    val postalError = "Please provide a valid postal code"
    val cityError = "Please provide a valid city"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.address_layout)
        initData()
        valid()
        btnOrderNow.text = context.getString(R.string.continue_text)
        btnOrderNow.setOnClickListener {
            if (valid()) {
                val address = Address()
                address.streetName = etStreet.text.toString().trim()
                address.streetNumber = etStreetNumber.text.toString().trim()
                address.city = etCity.text.toString().trim()
                address.postCode = etPostalCode.text.toString().trim()
                listener.onConfirm(address)
                dismiss()
            }

        }

        btnCancel.setOnClickListener { dismiss() }
    }

    private fun initData() {
        etStreet.setText(addressList[0])
        etStreetNumber.setText(addressList[1])
        etCity.setText(addressList[2])
        etPostalCode.setText(addressList[3])
    }


    private fun valid(): Boolean {
        etStreet.setError(null)
        etStreetNumber.setError(null)
        etCity.setError(null)
        etPostalCode.setError(null)
        var valid = true;
        if (etStreet.text.isNullOrEmpty()) {
            etStreet.setError(streetError)
            valid = false
        }
        if (etStreetNumber.text.isNullOrEmpty()) {
            etStreetNumber.setError(streetNumerError)
            valid = false
        }
        if (etCity.text.isNullOrEmpty()) {
            valid = false
            etCity.setError(cityError)
        }
        if (etPostalCode.text.isNullOrEmpty()) {
            etPostalCode.setError(postalError)
            valid = false

        }

        return valid;
    }
}