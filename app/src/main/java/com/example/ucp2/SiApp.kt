package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependeciesinjection.ContainerApp

class SiApp : Application() {

    //fungsinya untuk menyimpan instance containerApp dari dependencies injection
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()

        containerApp = ContainerApp(this)
    }
}