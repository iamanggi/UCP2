package com.example.ucp2.ui.viewModel

import com.example.ucp2.data.entity.MataKuliah

// Menyimpan input form ke dalam entity
fun MkEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
    kode = kode,
    namaMK = namaMK,
    SKS = SKS,
    semester = semester,
    jenisMK = jenisMK,
    dosenPengampu = namaDosen)

// Data class variabel yang menyimpan data input form
data class MkEvent(
    val kode: String = "",
    val namaMK: String = "",
    val SKS: String = "",
    val semester: String = "",
    val jenisMK: String = "",
    val namaDosen: String = ""
)
