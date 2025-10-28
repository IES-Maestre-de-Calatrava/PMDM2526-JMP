package view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import viewModel.CitasViewModel
import es.maestre.mvvm.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recuperar el viewModel
        val viewModel: CitasViewModel by viewModels()

        // Pintar pantallas
        binding.tvCita.text = viewModel.cita.cita
        binding.tvAutor.text = viewModel.cita.autor
    }

}