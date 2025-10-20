package es.maestre.holaandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.holaandroid.databinding.ActivityChipBinding

class ChipActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityChipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chip = binding.chip
        chip.setOnClickListener {
            // Acción a realizar al hacer clic en el Chip
            if(chip.isChecked()){
                chip.isCheckedIconVisible = true
                chip.text = "Llamada entrante..."
            }
            else{
                chip.text = "Llamada"
            }
        }

        val fab = binding.floatingActionButton
        fab.setOnClickListener { view ->
            // Acción a realizar al hacer clic en el FloatingActionButton
            // Ejemplo con mostrar un Toast
            Toast.makeText(this, "Fab pulsado", Toast.LENGTH_LONG).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}