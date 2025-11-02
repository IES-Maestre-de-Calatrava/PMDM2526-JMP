package es.maestre.sqlitemvvm.conexion

import androidx.lifecycle.LiveData
import es.maestre.sqlitemvvm.model.Peluche

class PelucheRepository(private val pelucheDAO: PelucheDAO) {
    fun getAllPeluches(): LiveData<List<Peluche>> {
        return pelucheDAO.getAllPeluches()
    }

    suspend fun insert(peluche: Peluche){
        pelucheDAO.insert(peluche)
    }

    suspend fun update(peluche: Peluche){
        pelucheDAO.update(peluche)

    }suspend fun delete(peluche: Peluche){
        pelucheDAO.delete(peluche)
    }

    fun getPelucheById(id: Int): LiveData<Peluche> {
        return pelucheDAO.getPelucheById(id)
    }


}