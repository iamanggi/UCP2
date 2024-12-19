package com.example.ucp2.ui.customwidget

import com.example.ucp2.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CustomBottomAppBar(
    onHomeClick: () -> Unit,
    onDosenClick: () -> Unit,
    onMkClick: () -> Unit,
    selectedItem: Int = 0,
    modifier: Modifier = Modifier
) {
    Row(
        Modifier
            .fillMaxWidth().background(color = colorResource(id = R.color.biru_tua))
            .padding(vertical = 15.dp, horizontal = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomBarIcon(
            icon = Icons.Default.Home,
            label = "Home",
            isSelected = selectedItem == 0
        ) {onHomeClick() }

        BottomBarIcon(
            icon = Icons.Default.Person,
            label = "Dosen",
            isSelected = selectedItem == 1
        ) {onDosenClick() }


        BottomBarIcon(
            icon = Icons.Default.AccountBox,
            label = "MataKuliah",
            isSelected = selectedItem == 2
        ) { onMkClick()}

    }
}

@Composable
fun BottomBarIcon(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(30.dp),
            tint = Color.White
        )
        Text(
            text = label,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun CustomBottomAppBarPreview() {
    var selectedItem by remember { mutableStateOf(0) }

    CustomBottomAppBar(
        onHomeClick = { selectedItem = 0 },
        onDosenClick = { selectedItem = 1 },
        onMkClick = { selectedItem = 2},
        selectedItem = selectedItem
    )
}

