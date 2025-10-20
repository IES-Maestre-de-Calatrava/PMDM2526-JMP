package es.maestre.holaandroid

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.holaandroid.databinding.ActivityButtonBinding
import es.maestre.holaandroid.databinding.ActivityRadioBactivityBinding

class RadioBActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRadioBactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRadioBactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGuardar.setOnClickListener {
            if (binding.radioButton.isChecked) {
                Toast.makeText(this, "Comprobar ubicación del usuario", Toast.LENGTH_LONG).show()
            }
        }

        binding.cbMostrarContra.setOnClickListener{
            if(binding.cbMostrarContra.isChecked){
                binding.etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                HideReturnsTransformationMethod.getInstance()
            }
            else{
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                PasswordTransformationMethod.getInstance()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}