package com.example.ucp2.ui.viewModel

import com.example.ucp2.data.entity.Dosen

data class HomeDosenUiState(
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)