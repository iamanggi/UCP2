package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route: String             //untuk menyimpan routing halaman
}

object DestinasiSplashView : AlamatNavigasi{
    override val route: String = "splash_view"
}

object DestinasiHome : AlamatNavigasi{   //object akan menjadi nama halaman/ menjadi pengenal halaman
    override val route: String = "home"
}

object DestinasiHomeDosen : AlamatNavigasi{
    override val route: String = "home_dosen"
}

object DestinasiInsertDosen : AlamatNavigasi{
    override val route: String = "insert_dosen"
}