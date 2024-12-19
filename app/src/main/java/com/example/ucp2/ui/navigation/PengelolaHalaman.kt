package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.ui.view.HomeDosenView
import com.example.ucp2.ui.view.HomeView
import com.example.ucp2.ui.view.SplashView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiSplashView.route
    ) {
        composable(route = DestinasiSplashView.route) {
            SplashView(
                onMulaiButton = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiSplashView.route) { inclusive = true }
                    }
                }
            )
        }

        // Menambahkan composable untuk DestinasiHome
        composable(route = DestinasiHome.route) {
            HomeView(
                onDosen = {
                    navController.navigate(DestinasiHomeDosen.route)
                },
                modifier = modifier
            )
        }

        // Menambahkan composable untuk DestinasiHomeDosen
        composable(route = DestinasiHomeDosen.route) {
            HomeDosenView(
                onHome = { navController.navigate(DestinasiHome.route) },
                onBack = { navController.popBackStack() },

            )
        }
    }
}
