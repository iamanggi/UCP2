package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositorySi(
    private val dosenDao: DosenDao
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
}