package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.customwidget.CustomBottomAppBar
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewModel.HomeMkUiState
import com.example.ucp2.ui.viewModel.HomeMkViewModel
import com.example.ucp2.ui.viewModel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeMkView(
    viewModel: HomeMkViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMk: () -> Unit = { },
    onDosen: () -> Unit = { },
    onMK: () -> Unit = { },
    onHome: () -> Unit = { },
    onBack:() -> Unit,
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier ) {

    Scaffold(topBar = {
        TopAppBar(
            judul = "Daftar Mata Kuliah",
            showBackButton = true,
            onBack = onBack,
            modifier = modifier
        )
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMk,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Mata Kuliah"
                )
            }
        },

        bottomBar = {
            CustomBottomAppBar(
                onHomeClick = onHome,
                onDosenClick =onDosen,
                onMkClick = onMK,
                selectedItem = 0
            )
        }) { innerPadding ->
        val homeUiState by viewModel.homeMKUiState.collectAsState()

        BodyHomeMKView(
            homeMKUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMKView(
    homeMKUiState: HomeMkUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } //Snackbar stste
    when {
        homeMKUiState.isLoading -> {
            // Menampilkan indikator loading
            Box (modifier= modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }

        homeMKUiState.isError -> {
            //menampilkan pesan error
            LaunchedEffect(homeMKUiState.errorMessage) {
                homeMKUiState.errorMessage?.let{message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)  //menampilkan snackbar
                    }
                }
            }
        }

        homeMKUiState.listMk.isEmpty() -> {
            //menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada Mata kuliah yang ditambahkan.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            //Menampilkan daftar mata kuliah
            ListMatakuliah(
                listMK = homeMKUiState.listMk,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = Modifier.padding(top = 100.dp)
            )
        }
    }
}