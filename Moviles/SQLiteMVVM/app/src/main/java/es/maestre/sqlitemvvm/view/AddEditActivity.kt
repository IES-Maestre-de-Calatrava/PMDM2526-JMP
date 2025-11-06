package es.maestre.sqlitemvvm.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.sqlitemvvm.R
import es.maestre.sqlitemvvm.databinding.ActivityAddEditBinding
import es.maestre.sqlitemvvm.databinding.ActivityMainBinding
import es.maestre.sqlitemvvm.model.Peluche
import es.maestre.sqlitemvvm.viewModel.PelucheViewModel
import androidx.activity.viewModels


class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding
    //variables peluche y mode
    private val viewModel: PelucheViewModel by viewModels()
    private var peluche: Peluche? = null
    private var mode: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupero lo que el intent me lanzó
        mode = intent.getStringExtra("MODE")
        peluche = intent.getSerializableExtra("PELUCHE") as Peluche?

        // hago un switch segun el modo que se ha pasado (ADD, DETAIL, EDIT, DELETE)
        when (mode) {
            "ADD" -> {
                title = "Añadir Peluche"
                setFieldsEditable(true)
                binding.btnEliminar.isEnabled = false
            }

            "DETAIL" -> {
                title = "Detalle del Peluche"

                peluche?.let { mostrarDatos(it) }
                setFieldsEditable(false)
                binding.btnGuardar.isEnabled = false
            }

            "EDIT" -> {
                title = "Editar Peluche"

                peluche?.let { mostrarDatos(it) }
                setFieldsEditable(true)
            }

            "DELETE" -> {
                title = "Borrar Peluche"

                peluche?.let { mostrarDatos(it) }
                setFieldsEditable(false)

            }
        }


        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val marca = binding.etMarca.text.toString()
            val material = binding.etMaterial.text.toString()
            val biografia = binding.etBiografia.text.toString()

            when (mode) {
                "ADD" -> {
                    val nuevoPeluche = Peluche(
                        nombre = nombre,
                        marca = marca,
                        material = material,
                        biografia = biografia
                    )

                    viewModel.insert(nuevoPeluche)
                    Toast.makeText(this, "Peluche añadido correctamente", Toast.LENGTH_SHORT).show()
                }

                "EDIT" -> {
                    peluche?.let {
                        val pelucheEditado = Peluche(
                            idPeluche = it.idPeluche,
                            nombre = nombre,
                            marca = marca,
                            material = material,
                            biografia = biografia
                        )

                        viewModel.update(pelucheEditado)
                        Toast.makeText(this, "Peluche editado correctamente", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            finish()
        }


        binding.btnEliminar.setOnClickListener {
            peluche?.let {
                viewModel.delete(it)
                Toast.makeText(this, "Peluche eliminado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }


    // mostrar los datos del peluche
    fun mostrarDatos(p: Peluche) {
        binding.etNombre.setText(p.nombre)
        binding.etMarca.setText(p.marca)
        binding.etMaterial.setText(p.material)
        binding.etBiografia.setText(p.biografia)
    }

    // hace que los campos sean editables o no
    fun setFieldsEditable(editable: Boolean) {
        binding.etNombre.isEnabled = editable
        binding.etMarca.isEnabled = editable
        binding.etMaterial.isEnabled = editable
        binding.etBiografia.isEnabled = editable
    }
}