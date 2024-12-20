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

object DestinasiHomeMK : AlamatNavigasi{
    override val route: String = "home_mk"
}
object DestinasiInsertMk : AlamatNavigasi{
    override val route: String = "insert_mk"
}

object DestinasiDetailMK : AlamatNavigasi{
    override val route = "detail_mk"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}

object DestinasiUpdateMK : AlamatNavigasi{
    override val route = "update_mk"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}