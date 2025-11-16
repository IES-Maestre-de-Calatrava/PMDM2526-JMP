package conexion

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import model.Abogado

@Dao
interface AbogadoDAO {
    @Insert
    suspend fun insert(abogado: Abogado)

    @Query("SELECT * FROM abogado")
    fun getAllAbogados(): LiveData<List<Abogado>>

    @Query("SELECT * FROM abogado WHERE id_abogado = :id")
    fun getAbogadoById(id: Int): LiveData<Abogado>

    @Update
    suspend fun update(abogado: Abogado)

    @Delete
    suspend fun delete(abogado: Abogado)
}