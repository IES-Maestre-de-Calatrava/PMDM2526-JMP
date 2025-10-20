package es.maestre.holaandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.holaandroid.databinding.ActivityInitBinding
import es.maestre.holaandroid.databinding.ActivityParamBinding

class ParamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParamBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityParamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtenemos la instancia del intent
        val intent = intent

        // Extrayendo los mensajes en tipo cadena
        val mensaje = intent.getStringExtra(ButtonActivity.EXTRA_MENSAJE)
        val telefono = intent.getStringExtra(ButtonActivity.EXTRA_TELEFONO)
        binding.textView.setText("$mensaje - $telefono")


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}