package com.abid.order.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abid.order.R
import com.abid.order.repository.Repository
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
            findNavController().navigate(R.id.action_locationSelectionFragment_to_menuFragment)
        }
        tvPickup.setOnClickListener { setSelectedPickup() }
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
