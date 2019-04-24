package com.electronicarmory.fragments

import android.app.Application

class FragmentsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        ObjectBox.init(this)
    }
}