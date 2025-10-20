package es.maestre.holaandroid

import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.holaandroid.databinding.ActivityMainBinding
import es.maestre.holaandroid.databinding.ActivityTextBinding
import org.intellij.lang.annotations.Pattern
import android.text.Editable
import android.text.TextWatcher

class TextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val products = resources.getStringArray(R.array.products)
        val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, products)
        binding.autoCompleteTextView.setAdapter(adapter)

        binding.telefono.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Justo ante de que el texto de EditText vaya a ser modificado
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Inmediatemente después de que los caracteres viejos han sido eliminados
                // pero antes de que los nuevos caracteres se muestren en la pantalla
            }
            override fun afterTextChanged(s: Editable?) {
                // Llama a la validación después de que el texto ha cambiado
                validarTelefono()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun validarTelefono(){
        if(Patterns.PHONE.matcher(binding.telefono.text.toString()).matches()){
            binding.telefono.setError(null)
        }
        else{
            binding.telefono.setError("Telefono no valido")
        }
    }
}