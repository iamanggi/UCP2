package com.example.ucp2.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositorySI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeMkViewModel(
    private val repositorySI: RepositorySI
) : ViewModel() {

    val homeMKUiState: StateFlow<HomeMkUiState> = repositorySI.getAllMK()
        .filterNotNull() // pastikan data tidak null
        .map { mkList ->
            HomeMkUiState(
                listMk = mkList,
                isLoading = false, // mematikan loading setelah data didapat
            )
        }
        .onStart {
            emit(HomeMkUiState(isLoading = true)) // tampilkan loading sebelum data datang
            delay(900)
        }
        .catch { exception ->
            emit(
                HomeMkUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = exception.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMkUiState(isLoading = true)
        )
}


data class HomeMkUiState(
    val listMk: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
