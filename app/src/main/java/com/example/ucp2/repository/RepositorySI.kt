package com.example.ucp2.repository

import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositorySI {
    suspend fun insertDosen(dosen: Dosen)
    fun getAllDosen(): Flow<List<Dosen>>
    fun getDosen(nidn: String) : Flow<Dosen>

    suspend fun insertMk(mataKuliah: MataKuliah)
    fun getAllMK(): Flow<List<MataKuliah>>
    fun getMK(kode: String) : Flow<MataKuliah>
    suspend fun deleteMk(mataKuliah: MataKuliah)
    suspend fun updateMk(mataKuliah: MataKuliah)
}