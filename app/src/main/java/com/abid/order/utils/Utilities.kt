package com.abid.order.utils

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import java.util.*


class Utilities {
    companion object {
        fun getUniqueId(): String {
            return UUID.randomUUID().toString()
        }

        fun setTitle(activity: FragmentActivity?, title: String) {
            activity?.actionBar?.title = title
        }
    }

}