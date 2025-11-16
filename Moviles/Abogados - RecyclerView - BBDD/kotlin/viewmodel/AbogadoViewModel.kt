package viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import conexion.AbogadoRepository
import conexion.AppDatabase
import kotlinx.coroutines.launch
import model.Abogado

class AbogadoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AbogadoRepository
    public val data: LiveData<List<Abogado>>

    init {
        val abogadoDAO = AppDatabase.getDatabase(application.applicationContext).abogadoDAO()
        data = abogadoDAO.getAllAbogados()
        repository = AbogadoRepository(abogadoDAO)
    }
    private fun getAllAbogados(): LiveData<List<Abogado>> {
        return repository.getAllAbogados()
    }

    fun getAbogadoById(id:Int):LiveData<Abogado> {
        return repository.getAbogadoById(id)
    }

    fun insert(abogado: Abogado) = viewModelScope.launch {
        repository.insert(abogado)
    }

    fun update(abogado: Abogado) = viewModelScope.launch{
        repository.update(abogado)
    }

    fun delete(abogado: Abogado) = viewModelScope.launch{
        repository.delete(abogado)
    }
}