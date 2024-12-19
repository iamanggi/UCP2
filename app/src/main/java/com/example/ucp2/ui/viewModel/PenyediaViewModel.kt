package com.example.ucp2.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.SiApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                SiApp().containerApp.repositorySI
            )
        }
        initializer {
            HomeDosenViewModel(
                SiApp().containerApp.repositorySI
            )
        }
        initializer {
            InsertDosenViewModel(
                SiApp().containerApp.repositorySI
            )
        }
    }
}

fun CreationExtras.SiApp(): SiApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SiApp)
