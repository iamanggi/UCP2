package com.example.ucp2.ui.viewModel

import com.example.ucp2.data.entity.MataKuliah

data class HomeMkUiState(
    val listMk: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
