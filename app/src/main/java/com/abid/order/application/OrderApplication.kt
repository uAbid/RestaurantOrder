package com.abid.order.application

import android.app.Application
import io.realm.Realm

class OrderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this);
    }
}