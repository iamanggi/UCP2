package com.example.ucp2.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositorySI
import kotlinx.coroutines.launch

class UpdateMkViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositorySI: RepositorySI
) : ViewModel(){
    var updateUiState by mutableStateOf(MkUiState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdateMK.KODE])

    init {
        viewModelScope.launch {
            repositorySI.getMKJoin(_kode).collect { mataKuliahList ->
                val mataKuliah = mataKuliahList.firstOrNull()
                if (mataKuliah != null) {
                    updateUiState = mataKuliah.toUIStateMk()
                } else {
                }
            }
        }
    }

    fun updateState(mkEvent: MkEvent){
        updateUiState = updateUiState.copy(
            mkEvent = mkEvent,
        )
    }

    fun validateFields(): Boolean{
        val event = updateUiState.mkEvent
        val errorState = FormErrorStateMK(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            namaMK = if (event.namaMK.isNotEmpty()) null else "Nama Mata Kuliah tidak boleh kosong",
            SKS = if (event.SKS.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenisMK = if (event.jenisMK.isNotEmpty()) null else "Jenis Mata Kuliah tidak boleh kosong",
            namaDosen = if (event.namaDosen.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong",
        )

        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return  errorState.isValid()
    }

    fun updateData(){
        val currentEvent = updateUiState.mkEvent

        if (validateFields()){
            viewModelScope.launch {
                try{
                    repositorySI.updateMk(currentEvent.toMataKuliahEntity())
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        mkEvent = MkEvent(),
                        isEntryValid = FormErrorStateMK()
                    )
                    println("snackBarMessage diatur: ${updateUiState.snackBarMessage}")
                } catch (e: Exception){
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else{
            updateUiState = updateUiState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage(){
        updateUiState = updateUiState.copy(snackBarMessage = null)
    }
}
fun MataKuliah.toUIStateMk(): MkUiState {
    return MkUiState(
        mkEvent = this.toDetailMKUiEvent()
    )
}