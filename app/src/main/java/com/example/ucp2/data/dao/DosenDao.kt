package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {

    //fungsi get all dosen diurutkan berdasarkan nama dosen dari huruf A - Z
    @Query("SELECT * FROM dosen ORDER BY namaDosen ASC")
    fun getAllDosen() : Flow<List<Dosen>>

    //mengambil data dosen berdasarkan nidn
    @Query("SELECT * FROM dosen WHERE nidn = :nidn")
    fun getDosen(nidn: String) : Flow<Dosen>

    // menambahkan fungsi untuk insert data dosen
    @Insert
    suspend fun insertDosen(dosen: Dosen)
}
