package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.DetailMkView
import com.example.ucp2.ui.view.HomeDosenView
import com.example.ucp2.ui.view.HomeMkView
import com.example.ucp2.ui.view.HomeView
import com.example.ucp2.ui.view.InsertDosenView
import com.example.ucp2.ui.view.InsertMkView
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
                onMK = {navController.navigate(DestinasiHomeMK.route)},
                modifier = modifier
            )
        }

        // Menambahkan composable untuk DestinasiHomeDosen
        composable(route = DestinasiHomeDosen.route) {
            HomeDosenView(
                onAddDosen = {
                    navController.navigate(DestinasiInsertDosen.route)
                },
                onHome = { navController.navigate(DestinasiHome.route) },
                onBack = { navController.popBackStack() },
                onMK = {navController.navigate(DestinasiHomeMK.route)}
            )
        }
        composable(
            route = DestinasiInsertDosen.route
        ){
            InsertDosenView(onBack = {
                navController.popBackStack()
            },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier)
        }
        composable(
            route = DestinasiHomeMK.route
        ){
            HomeMkView(onBack = { navController.popBackStack() },
                onAddMk = { navController.navigate(DestinasiInsertMk.route) },
                onHome = { navController.navigate(DestinasiHome.route) },
                onDosen = { navController.navigate(DestinasiHomeDosen.route)},
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetailMK.route}/$kode")
                    println("PengelolaHalaman: kode = $kode")},
                modifier = modifier)

        }
        composable(
            route = DestinasiInsertMk.route
        ){
            InsertMkView(onBack = {
                navController.popBackStack()
            },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier)
        }

    }
}
