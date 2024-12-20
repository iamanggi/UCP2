package com.example.ucp2.ui.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositorySI
import com.example.ucp2.ui.navigation.DestinasiDetailMK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailMKViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySI: RepositorySI
) : ViewModel() {

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetailMK.KODE])

    val detailUiState: StateFlow<DetailUiState> = repositorySI.getMK(_kode)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailMKUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue =
            DetailUiState(isLoading = true)
        )


    fun deleteMk(){
        detailUiState.value.detailUiEvent.toMataKuliahEntity().let {
            viewModelScope.launch {
                repositorySI.deleteMk(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: MkEvent = MkEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUIEventEmpty: Boolean
        get() = detailUiEvent == MkEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MkEvent()
}

fun MataKuliah.toDetailMKUiEvent(): MkEvent {
    return MkEvent(
        kode = kode,
        namaMK = namaMK,
        SKS = SKS,
        semester = semester,
        jenisMK = jenisMK,
        namaDosen = namaDosen
    )
}

