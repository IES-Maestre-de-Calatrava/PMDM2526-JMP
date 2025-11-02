package es.maestre.sqlitemvvm.conexion

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.maestre.sqlitemvvm.model.Peluche

@Dao
interface  PelucheDAO {
    @Insert
    suspend fun insert(peluche: Peluche)

    @Query("SELECT * FROM peluche")
    fun getAllPeluches(): LiveData<List<Peluche>>

    @Query("SELECT * FROM peluche WHERE id_Peluche = :id")
    fun getPelucheById(id: Int): LiveData<Peluche>

    @Update
    suspend fun update (peluche: Peluche)

    @Delete
    suspend fun delete(peluche:Peluche)



}
