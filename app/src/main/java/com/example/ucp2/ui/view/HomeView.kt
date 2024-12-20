package com.example.ucp2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.ui.customwidget.CustomBottomAppBar
import com.example.ucp2.ui.viewModel.HomeViewModel
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewModel.HomeUiState
import com.example.ucp2.ui.viewModel.PenyediaViewModel

@Composable
fun HomeView(
    onDetailClick: (String) -> Unit = { },
    onDosen: () -> Unit = { },
    onMK: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(colorResource(id = R.color.biru_004),
                        shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)),
                contentAlignment = Alignment.Center
            ) {
                    TopAppBar(
                        judul = "SIAKAD+",
                        showBackButton = false,
                        onBack = { },
                        modifier = modifier
                    )
                }

        },
        bottomBar = {
            CustomBottomAppBar(
                onHomeClick = {  },
                onDosenClick =onDosen,
                onMkClick = onMK,
                selectedItem = 0
            )
        },

        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                BodyHomeView(
                    modifier = Modifier.padding(innerPadding),
                    onClick = onDetailClick,
                    onDosen = onDosen,
                    onMK = onMK
                )

            }
        }
    )
}

@Composable
fun BodyHomeView(
    homeUiState: HomeUiState = HomeUiState(),
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { },
    onDosen: () -> Unit,
    onMK: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box Dosen
            BoxDosen(
                label = "Dosen",
                onClick = onDosen
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Box Mata kuliah
            BoxMk(
                label = "Mata Kuliah",
                onClick = onMK
            )
        }
    }
}

@Composable
fun BoxDosen(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .width(150.dp)
            .height(150.dp)
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.biru_004))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
@Composable
fun BoxMk(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .width(150.dp)
            .height(150.dp)
            .padding(8.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.biru_004))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
