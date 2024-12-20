package com.example.ucp2.ui.viewModel

import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah

// Untuk merubah state/tampilan
data class MkUiState(
    val mkEvent: MkEvent = MkEvent(),
    val isEntryValid: FormErrorStateMK = FormErrorStateMK(),
    val snackBarMessage: String? = null,
    val listDosen: List<Dosen> = emptyList()
)

// Fungsi untuk validasi
data class FormErrorStateMK(
    val kode: String? = null,
    val namaMK: String? = null,
    val SKS: String? = null,
    val semester: String? = null,
    val jenisMK: String? = null,
    val namaDosen: String? = null
) {
    fun isValid(): Boolean {
        return kode == null && namaMK == null && SKS == null &&
                semester == null && jenisMK == null && namaDosen == null
    }
}

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
