package es.maestre.holaandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import es.maestre.holaandroid.databinding.ActivityInitBinding


class InitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // CONFIGURACIÓN DE MATERIAL TOOLBAR
        val toolbar: MaterialToolbar = binding.toolbar // Obtenemos la toolbar por su ID
        setSupportActionBar(toolbar) // Establecer como la ActionBar

        // Personalización del icono de navegación y su acción
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_menu) // Reemplaza ic_menu
        toolbar.setNavigationOnClickListener {
            // Acción para el icono de navegación (e.g., abrir un Drawer Layout)
            Toast.makeText(this, "Icono de Navegación pulsado", Toast.LENGTH_SHORT).show()
        }



        binding.buttonText.setOnClickListener {
            val intent = Intent(this, TextActivity::class.java)
            startActivity(intent)
        }

        binding.buttonMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonButton.setOnClickListener {
            val intent = Intent(this, ButtonActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRButton.setOnClickListener {
            val intent = Intent(this, RadioBActivity::class.java)
            startActivity(intent)
        }

        binding.buttonChip.setOnClickListener {
            val intent = Intent(this, ChipActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLayout.setOnClickListener {
            val intent = Intent(this, LayoutActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    // *** PASO 7 DEL PDF: ASIGNAR FICHERO DE MENÚ (Inflar menú) ***
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mi_menu, menu)
        return true
    }
    // ***************************************************************

    // *** PASO 8 DEL PDF: MANEJAR CLICS EN LOS ELEMENTOS DEL MENÚ ***
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                // Acción para el elemento de menú "Búsqueda"
                Toast.makeText(this, "Acción: Buscar", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_info -> {
                // Acción para el elemento de menú "Info"
                Toast.makeText(this, "Acción: Información", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_configuracion -> {
                // Acción para el elemento de menú "Configuración"
                Toast.makeText(this, "Acción: Configuración", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}