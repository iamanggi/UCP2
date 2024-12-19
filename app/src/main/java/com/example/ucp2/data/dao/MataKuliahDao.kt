package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {

    @Query("SELECT * FROM matakuliah ORDER BY namaMK ASC")
    fun getAllMatakuliah() : Flow<List<MataKuliah>>

    // menambahkan fungsi untuk mengambil data berdasarkan kode mata kuliah
    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getMatakuliah(kode: String) : Flow<MataKuliah>

    // menambahkan operasi untuk menambahkan data pada tabel mata kuliah
    @Insert
    suspend fun insertMatakuliah(mataKuliah: MataKuliah)

    // menambahkan operasi untuk memperbarui data di tabel mata kuliah
    @Update
    suspend fun updateMatakuliah(mataKuliah: MataKuliah)

    // menambahkan operasi untuk menhapus data di tabel mata kuliah
    @Delete
    suspend fun  deleteMatakuliah(mataKuliah: MataKuliah)

    @Query("""
        SELECT mk.kode, mk.namaMK, mk.SKS, mk.semester, mk.jenisMK, d.namaDosen 
        FROM matakuliah mk
        INNER JOIN dosen d ON mk.dosenPengampu = d.nidn
        WHERE mk.kode = :kode
        ORDER BY mk.namaMK ASC
    """)
    fun getMatakuliahJoin(kode: String): Flow<List<MataKuliah>>
}