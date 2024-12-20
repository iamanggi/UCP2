package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.customwidget.DynamicSelectedTextField
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewModel.FormErrorStateMK
import com.example.ucp2.ui.viewModel.InsertMkViewModel
import com.example.ucp2.ui.viewModel.MkEvent
import com.example.ucp2.ui.viewModel.MkUiState
import com.example.ucp2.ui.viewModel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertMkView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    onDosen: () -> Unit = { },
    onMK: () -> Unit = { },
    viewModel: InsertMkViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val mkUiState = viewModel.mkUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(mkUiState.snackBarMessage) {
        mkUiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Mata Kuliah",

                )

            InsertBodyMk(
                uiState = mkUiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyMk(
    modifier: Modifier = Modifier,
    onValueChange: (MkEvent) -> Unit,
    uiState: MkUiState,
    onClick: () -> Unit,

    ) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMatakuliah(
            mkEvent = uiState.mkEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            listDosen = uiState.listDosen,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
        ) {
            Text(text = "Simpan", fontSize = 16.sp)
        }
    }
}

@Composable
fun FormMatakuliah(
    mkEvent: MkEvent = MkEvent(),
    onValueChange: (MkEvent) -> Unit = {},
    errorState: FormErrorStateMK = FormErrorStateMK(),
    listDosen: List<Dosen> = emptyList(),
    modifier: Modifier = Modifier
) {
    val jenisMk = listOf("Peminatan", "Wajib")
    val semester = listOf("Genap", "Ganjil")

    Box() {
        Column(modifier = modifier.fillMaxWidth().padding(top = 20.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = mkEvent.kode,
                onValueChange = { onValueChange(mkEvent.copy(kode = it)) },
                label = { Text("Kode") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Info,
                        contentDescription = "")
                },
                isError = errorState.kode != null,
                placeholder = { Text("Masukkan Kode Mata kuliah") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(30.dp)
            )
            Text(text = errorState.kode ?: "", color = Color.Red)

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = mkEvent.namaMK,
                onValueChange = { onValueChange(mkEvent.copy(namaMK = it)) },
                label = { Text("Mata Kuliah") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.AccountBox,
                        contentDescription = "")
                },
                isError = errorState.namaMK != null,
                placeholder = { Text("Masukkan Nama Mata Kuliah") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                shape = RoundedCornerShape(30.dp)
            )
            Text(text = errorState.namaMK ?: "", color = Color.Red)

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = mkEvent.SKS,
                onValueChange = { onValueChange(mkEvent.copy(SKS = it)) },
                label = { Text("SKS") },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.DateRange,
                        contentDescription = "")
                },
                isError = errorState.SKS != null,
                placeholder = { Text("Masukkan SKS") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                shape = RoundedCornerShape(30.dp)
            )
            Text(text = errorState.SKS ?: "", color = Color.Red)

            Text(text = "Semester")
            Row(modifier = Modifier.fillMaxWidth())
            {
                semester.forEach { smt ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    )
                    {
                        RadioButton(
                            selected = mkEvent.semester == smt,
                            onClick = {
                                onValueChange(mkEvent.copy(semester = smt))
                            },
                        )
                        Text(text = smt)
                    }
                }
            }
            Text(
                text = errorState.semester ?: "",
                color = Color.Red
            )

            Text(text = "Jenis Mata Kuliah")
            Row(modifier = Modifier.fillMaxWidth())
            {
                jenisMk.forEach { jmk ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    )
                    {
                        RadioButton(
                            selected = mkEvent.jenisMK == jmk,
                            onClick = {
                                onValueChange(mkEvent.copy(jenisMK = jmk))
                            },
                        )
                        Text(text = jmk)
                    }
                }
            }
            Text(
                text = errorState.jenisMK ?: "",
                color = Color.Red
            )
            DynamicSelectedTextField(
                selectedValue = mkEvent.namaDosen,
                listDosen = listDosen,
                label = "Pilih Dosen Pengampu",
                onValueChangedEvent = { selectedDosen ->
                    onValueChange(mkEvent.copy(namaDosen = selectedDosen))
                },
                modifier = Modifier.fillMaxWidth(),

            )

        }
    }
}