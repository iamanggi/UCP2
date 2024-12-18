package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MataKuliahDao
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositorySi(
    private val dosenDao: DosenDao,
    private val matakuliahDao: MataKuliahDao
):RepositorySI {
    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }
    override fun getDosen(nidn: String): Flow<Dosen> {
        return dosenDao.getDosen(nidn)
    }
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    //Implementasi Operasi Mata kuliah
    override fun getAllMK(): Flow<List<MataKuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }

    override fun getMK(kode: String): Flow<MataKuliah> {
        return matakuliahDao.getMatakuliah(kode)
    }

    override suspend fun insertMk(mataKuliah: MataKuliah) {
        matakuliahDao.insertMatakuliah(mataKuliah)
    }

    override suspend fun updateMk(mataKuliah: MataKuliah) {
        matakuliahDao.insertMatakuliah(mataKuliah)
    }

    override suspend fun deleteMk(mataKuliah: MataKuliah) {
        matakuliahDao.insertMatakuliah(mataKuliah)
    }
}