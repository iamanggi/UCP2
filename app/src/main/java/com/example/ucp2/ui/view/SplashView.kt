package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R


@Composable
fun SplashView(
    onMulaiButton: () -> Unit
){
    Column (modifier = Modifier.fillMaxSize()
        .background(color = colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        //menambahkan image
        Image(
            painter = painterResource(
                id = R.drawable.logo_pam
            ),
            contentDescription = "", modifier = Modifier.size(280.dp)
        )

        //menambahkan Button untuk kehalaman berikutnya
        Button(onClick = {onMulaiButton()},
            modifier = Modifier.fillMaxWidth().padding(24.dp),  colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.biru_muda),
                contentColor = colorResource(id = R.color.white)
            ))
        {
            Text(text = "Mulai", style = TextStyle(fontSize = 18.sp))
        }

    }

}