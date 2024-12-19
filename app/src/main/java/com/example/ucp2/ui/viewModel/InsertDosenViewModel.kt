package com.example.ucp2.ui.viewModel

import com.example.ucp2.data.entity.Dosen

//berfungsi untuk validasi
data class FormErrorState(
    val nidn: String? = null,
    val namaDosen: String? = null,
    val jenisKelamin: String? = null,
){
    fun isValid():Boolean{
        return nidn == null && namaDosen == null && jenisKelamin == null
    }
}

//menyimpan input form ke dalam entity
fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    namaDosen = namaDosen,
    jenisKelamin = jenisKelamin
)

//data class variabel yang menyimpan data input form
data class DosenEvent(
    val nidn: String = "",
    val namaDosen: String = "",
    val jenisKelamin: String = ""
)