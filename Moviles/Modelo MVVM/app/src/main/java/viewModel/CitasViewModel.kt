package viewModel

import androidx.lifecycle.ViewModel
import model.CitaModel
import model.ProveedorCitas

class CitasViewModel: ViewModel() {

    // Lógica para interactuar con el Modelo y preparar Datos
    var cita: CitaModel

    init {
        cita = ProveedorCitas.getCitaRandom()
    }
}