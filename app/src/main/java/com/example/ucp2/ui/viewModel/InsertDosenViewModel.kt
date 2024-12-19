package com.example.ucp2.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositorySI
import kotlinx.coroutines.launch

class InsertDosenViewModel(private val repositorySI: RepositorySI) : ViewModel(){
    var uiState by mutableStateOf(DosenUiState())

    // memperbarui state berdasarkan input pengguna
    fun updateState(dosenEvent: DosenEvent){
        uiState = uiState.copy(
            dosenEvent = dosenEvent,
        )
    }

    //validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            namaDosen = if (event.namaDosen.isNotEmpty()) null else "Nama dosen tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    //menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState.dosenEvent
        if (validateFields()){
            viewModelScope.launch {
                try{
                    repositorySI.insertDosen(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                }
                catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal disimpan"
                    )
                }
            }
        } else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. periksa kembali data anda"
            )
        }
    }
    //Reset pesan snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

//untuk merubah state/ tampilan
data class DosenUiState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

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