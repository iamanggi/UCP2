package com.example.ucp2.dependeciesinjection

import android.content.Context
import com.example.ucp2.data.database.SiDatabase
import com.example.ucp2.repository.LocalRepositorySi
import com.example.ucp2.repository.RepositorySI

interface InterfacesContainerApp {
    val repositorySI: RepositorySI
}

class ContainerApp(private val context: Context) : InterfacesContainerApp{
    override val repositorySI: RepositorySI by lazy {
        val database = SiDatabase.getDatabase(context)
        LocalRepositorySi(database.dosenDao(), database.matakuliahDao())
    }

}