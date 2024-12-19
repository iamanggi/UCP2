package com.example.ucp2.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositorySI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeDosenViewModel(
    private val repositorySI: RepositorySI
) : ViewModel() {

    val homeDsnUiState: StateFlow<HomeDosenUiState> = repositorySI.getAllDosen()
        .filterNotNull() // memastikan data tidak null
        .map { dosenList ->
            HomeDosenUiState(
                listDosen = dosenList,
                isLoading = false, // mematikan loading setelah data didapat
            )
        }
        .onStart {
            emit(HomeDosenUiState(isLoading = true)) // tampilkan loading sebelum data datang
            delay(900)
        }
        .catch { exception ->
            emit(
                HomeDosenUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = exception.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeDosenUiState(isLoading = true)
        )
}


data class HomeDosenUiState(
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)