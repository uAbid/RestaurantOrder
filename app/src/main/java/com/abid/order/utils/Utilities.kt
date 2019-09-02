package com.abid.order.utils

import java.util.*


class Utilities {
    companion object {
        fun getUniqueId(): String {
            return UUID.randomUUID().toString()
        }
    }

}