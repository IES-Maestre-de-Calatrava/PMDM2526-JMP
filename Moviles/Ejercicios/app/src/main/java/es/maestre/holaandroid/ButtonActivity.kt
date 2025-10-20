package es.maestre.holaandroid

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.holaandroid.databinding.ActivityButtonBinding
import android.view.View
import android.text.Editable
import android.text.TextWatcher


class ButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityButtonBinding

    companion object{
        val EXTRA_MENSAJE: String = "MENSAJE"
        val EXTRA_TELEFONO: String = "TELEFONO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener (){
            validarTelefono(binding.telefono.text.toString())
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, ParamActivity::class.java)
            intent.putExtra(EXTRA_TELEFONO, binding.telefono.text.toString())
            intent.putExtra(EXTRA_MENSAJE, binding.mensaje.text.toString())
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun validarTelefono(telefono : String){
        if(Patterns.PHONE.matcher(telefono).matches()){
            binding.telefono.setError(null)
        }
        else{
            binding.telefono.setError("Telefono no valido")
        }
    }
}