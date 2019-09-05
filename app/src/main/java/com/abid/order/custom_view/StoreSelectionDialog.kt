package com.abid.order.custom_view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.abid.order.Constants
import com.abid.order.R
import com.abid.order.listeners.DialogListener
import com.abid.order.repository.Repository
import kotlinx.android.synthetic.main.btn_layout.*
import kotlinx.android.synthetic.main.store_selection_dailog.*

open class StoreSelectionDialog(context: Context?, var listeners: DialogListener) :
    Dialog(context) {


    private var selectedStore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_selection_dailog)
        initData()
        tvStoreB.setOnClickListener {
            setSelectedStoreB()
        }
        tvStoreA.setOnClickListener {
            setSelectedStoreA()
        }

        btnOrderNow.setOnClickListener {
            listeners.onConfirm(selectedStore)
            dismiss()
        }
        btnCancel.setOnClickListener { dismiss() }

    }

    private fun setSelectedStoreB() {
        tvStoreA.background = ContextCompat.getDrawable(context, R.drawable.bg_unselected)
        tvStoreB.background = ContextCompat.getDrawable(context, R.drawable.bg_selected)
        tvStoreA.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        tvStoreB.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
        selectedStore = 1
    }

    private fun setSelectedStoreA() {
        selectedStore = 0
        tvStoreA.background = ContextCompat.getDrawable(context, R.drawable.bg_selected)
        tvStoreB.background = ContextCompat.getDrawable(context, R.drawable.bg_unselected)
        tvStoreA.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
        tvStoreB.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
    }

    private fun initData() {
        val order = Repository.instance.getCurrentOrder()
        if (order?.store.equals(Constants.STORE_A)) {
            setSelectedStoreA()
        } else if (order?.store.equals(Constants.STORE_B)) {
            setSelectedStoreB()
        }

    }
}