package com.example.ucp2.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositorySI
import kotlinx.coroutines.launch

class InsertMkViewModel(private val repositorySI: RepositorySI) : ViewModel() {
    var mkUiState by mutableStateOf(MkUiState())

    init {
        // Mengambil data dosen ketika view model diinisialisasi
        getListDosen()
    }

    // Fungsi untuk mengambil data dosen dari repository
    private fun getListDosen() {
        viewModelScope.launch {
            try {
                // Menggunakan getAllDosen() untuk mengambil daftar dosen
                repositorySI.getAllDosen().collect { dosenList ->
                    mkUiState = mkUiState.copy(listDosen = dosenList)
                }
            } catch (e: Exception) {
                // Menangani error jika data gagal diambil
                mkUiState = mkUiState.copy(snackBarMessage = "Gagal mengambil data dosen")
            }
        }
    }

    // Memperbarui state berdasarkan input pengguna
    fun updateState(mkEvent: MkEvent) {
        mkUiState = mkUiState.copy(mkEvent = mkEvent)
    }

    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = mkUiState.mkEvent
        val errorState = FormErrorStateMK(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            namaMK = if (event.namaMK.isNotEmpty()) null else "Nama Mata Kuliah tidak boleh kosong",
            SKS = if (event.SKS.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenisMK = if (event.jenisMK.isNotEmpty()) null else "Jenis Mata Kuliah tidak boleh kosong",
            namaDosen = if (event.namaDosen.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong",
        )

        mkUiState = mkUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Menyimpan data ke repository
    fun saveData() {
        val currentEvent = mkUiState.mkEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySI.insertMk(currentEvent.toMataKuliahEntity())
                    mkUiState = mkUiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        mkEvent = MkEvent(),
                        isEntryValid = FormErrorStateMK()
                    )
                } catch (e: Exception) {
                    mkUiState = mkUiState.copy(
                        snackBarMessage = "Data Gagal disimpan"
                    )
                }
            }
        } else {
            mkUiState = mkUiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

    fun resetSnackBarMessage() {
        mkUiState = mkUiState.copy(snackBarMessage = null)
    }
}

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
