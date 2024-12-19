package com.example.ucp2.ui.viewModel

import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah

data class HomeUiState(
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val listMk: List<MataKuliah> = listOf()
)