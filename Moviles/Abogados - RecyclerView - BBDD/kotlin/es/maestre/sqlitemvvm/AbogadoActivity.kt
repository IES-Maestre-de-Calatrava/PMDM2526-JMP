package es.maestre.sqlitemvvm

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.sqlitemvvm.databinding.ActivityAbogadoBinding
import model.Abogado
import viewmodel.AbogadoViewModel
import kotlin.getValue

class AbogadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAbogadoBinding

    val viewmodel: AbogadoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAbogadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recuperar los datos del Intent
        var mode = intent.getStringExtra("mode")
        var idAbogado:Long = 0
        when (mode) {
            "NEW" -> {
                idAbogado = 0
            }
            "DETAIL" -> {
                var currentAbogado = intent.getSerializableExtra("abogado_data", Abogado::class.java)
                idAbogado = currentAbogado?.idAbogado ?: 0
                binding.nombre.setText(currentAbogado?.nombre)
                binding.telefono.setText(currentAbogado?.telefono)
                binding.especialidad.setText(currentAbogado?.especialidad)
                binding.biografia.setText(currentAbogado?.biografia)
                //poner campos deshabilitados
                binding.nombre.setEnabled(false)
                binding.telefono.setEnabled(false)
                binding.especialidad.setEnabled(false)
                binding.biografia.setEnabled(false)

                //boton guardar no visible
                binding.btnGuardar.setVisibility(View.GONE)
            }
            "EDIT" -> {
                var currentAbogado = intent.getSerializableExtra("abogado_data", Abogado::class.java)
                idAbogado = currentAbogado?.idAbogado ?: 0
                binding.nombre.setText(currentAbogado?.nombre)
                binding.telefono.setText(currentAbogado?.telefono)
                binding.especialidad.setText(currentAbogado?.especialidad)
                binding.biografia.setText(currentAbogado?.biografia)
            }
        }

        binding.btnGuardar.setOnClickListener {
            val abogado = Abogado( idAbogado,binding.nombre.text.toString(),
                binding.telefono.text.toString(),
                binding.especialidad.text.toString(),
                binding.biografia.text.toString())
            when (mode) {
                "NEW" -> {
                        //crear abogado
                        viewmodel.insert(abogado)
                        Toast.makeText(this,"Abogado creado", Toast.LENGTH_LONG).show()
                        //esperar 3 segundos y cerrar pantalla para volver a la anterior
                        Thread.sleep(3000)
                        finish()
                }
                "EDIT" -> {
                        //editar abogado
                        viewmodel.update(abogado)
                        Toast.makeText(this,"Abogado modificado", Toast.LENGTH_LONG).show()
                        //esperar 3 segundos y cerrar pantalla para volver a la anterior
                        Thread.sleep(3000)
                        finish()
                }

            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}