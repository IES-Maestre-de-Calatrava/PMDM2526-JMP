package es.maestre.sqlitemvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import es.maestre.sqlitemvvm.conexion.PelucheRepository
import es.maestre.sqlitemvvm.conexion.AppDatabase
import kotlinx.coroutines.launch
import es.maestre.sqlitemvvm.model.Peluche

class PelucheViewModel (application: Application) : AndroidViewModel(application)
{
    private val repository: PelucheRepository
    val data: LiveData<List<Peluche>>

    init {
        val pelucheDAO =
            AppDatabase.getDatabase(application.applicationContext).pelucheDAO()

        data = pelucheDAO.getAllPeluches()
        repository = PelucheRepository(pelucheDAO)
    }


    private fun getAllPeluches(): LiveData<List<Peluche>>{
        return repository.getAllPeluches()
    }

    fun getPelucheById(id: Int): LiveData<Peluche>{
        return repository.getPelucheById(id)
    }

    fun insert (peluche: Peluche) = viewModelScope.launch{
        repository.insert(peluche)
    }

    fun update (peluche: Peluche) = viewModelScope.launch{
        repository.update(peluche)
    }

    fun delete (peluche: Peluche) = viewModelScope.launch{
        repository.delete(peluche)
    }
}