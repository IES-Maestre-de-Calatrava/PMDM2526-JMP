package es.maestre.sqlitemvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.sqlitemvvm.R
import es.maestre.sqlitemvvm.databinding.ActivityMainBinding
import es.maestre.sqlitemvvm.model.Peluche
import es.maestre.sqlitemvvm.view.adapter.PelucheAdapter
import es.maestre.sqlitemvvm.viewModel.PelucheViewModel

class MainActivity : AppCompatActivity() {

    private var selected: Peluche? = null
    private val viewModel: PelucheViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esta linea le dice a mi main, que es el topToolbar mi ACTIONBAR OFICIAL, si no no furula
        setSupportActionBar(binding.topToolbar)


        // Configurar el RecyclerView
        val adapter = PelucheAdapter(emptyList()) { peluche ->
            selected = peluche
            Toast.makeText(this, "Seleccionado: ${peluche.nombre}", Toast.LENGTH_SHORT).show()
        }

        binding.rvPeluches.adapter = adapter
        binding.rvPeluches.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)


        // Ajuste visual (esto está bien, lo dejamos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mi_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                Toast.makeText(this, "Pulsado AÑADIR", Toast.LENGTH_SHORT).show()
                val i = Intent(this, AddEditActivity::class.java)
                i.putExtra("MODE", "ADD")
                startActivity(i)
                return true
            }

            R.id.action_detail -> {
                if (selected == null) {
                    Toast.makeText(this, "Selecciona un peluche primero", Toast.LENGTH_SHORT).show()
                } else {
                    val i = Intent(this, AddEditActivity::class.java)
                    i.putExtra("MODE", "DETAIL")
                    i.putExtra("PELUCHE", selected)
                    startActivity(i)
                }
                return true
            }

            R.id.action_edit -> {
                if (selected == null) {
                    Toast.makeText(this, "Selecciona un peluche primero", Toast.LENGTH_SHORT).show()
                } else {
                    val i = Intent(this, AddEditActivity::class.java)
                    i.putExtra("MODE", "EDIT")
                    i.putExtra("PELUCHE", selected)
                    startActivity(i)
                }
                return true
            }

            R.id.action_delete -> {
                if (selected == null) {
                    Toast.makeText(this, "Selecciona un peluche primero", Toast.LENGTH_SHORT).show()
                } else {
                    val i = Intent(this, AddEditActivity::class.java)
                    i.putExtra("MODE", "DELETE")
                    i.putExtra("PELUCHE", selected)
                    startActivity(i)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Actualiza la lista de peluches cuando se reanuda la actividad
    override fun onResume() {
        super.onResume()
        viewModel.data.observe(this) { peluches ->
            (binding.rvPeluches.adapter as PelucheAdapter).updateData(peluches)
        }
    }
}
