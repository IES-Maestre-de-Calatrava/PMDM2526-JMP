package es.maestre.holaandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.holaandroid.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCalculatorBinding

    private var primerNumero: Double = 0.0
    private var operacion: String? = null

    private var limpiarPantalla = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Limpiador
        binding.BLimpiar.setOnClickListener {
            reiniciarCalculadora()
        }

        /* Lógica de los numeros */

        binding.B0.setOnClickListener { anadirNumero("0") }
        binding.B1.setOnClickListener { anadirNumero("1") }
        binding.B2.setOnClickListener { anadirNumero("2") }
        binding.B3.setOnClickListener { anadirNumero("3") }
        binding.B4.setOnClickListener { anadirNumero("4") }
        binding.B5.setOnClickListener { anadirNumero("5") }
        binding.B6.setOnClickListener { anadirNumero("6") }
        binding.B7.setOnClickListener { anadirNumero("7") }
        binding.B8.setOnClickListener { anadirNumero("8") }
        binding.B9.setOnClickListener { anadirNumero("9") }


        binding.BDecimal.setOnClickListener {
            val pantalla = binding.textViewResult
            if (!pantalla.text.contains(".")) {
                pantalla.append(".")
            }
        }

        // Ahora se hacen los de las operaciones
        fun prepararOperacion(op: String) {
            primerNumero = binding.textViewResult.text.toString().toDoubleOrNull() ?: 0.0
            operacion = op
            limpiarPantalla = true // Activamos la bandera para limpiar.
            Toast.makeText(this, "Operación: $op", Toast.LENGTH_SHORT).show()
        }

        binding.BSumar.setOnClickListener { prepararOperacion("+") }
        binding.BRestar.setOnClickListener { prepararOperacion("-") }
        binding.BMultiplicar.setOnClickListener { prepararOperacion("*") }
        binding.BDividir.setOnClickListener { prepararOperacion("/") }

        /*Por ultimo hacemos el de resultado*/
        binding.BResultado.setOnClickListener {

            if(operacion == null) return@setOnClickListener

            val segundoNumero = binding.textViewResult.text.toString().toDouble() ?: 0.0
            var resultado: Double? = null

            when (operacion) {
                "+" -> {
                    resultado = primerNumero + segundoNumero
                }

                "-" -> {
                    resultado = primerNumero - segundoNumero
                }

                "*" -> {
                    resultado = primerNumero * segundoNumero
                }

                "/" -> {
                    resultado = primerNumero / segundoNumero
                }
            }

            // Mostramos el resultado y reiniciamos el estado para la próxima operación
            binding.textViewResult.text = resultado.toString()
            operacion = null
            limpiarPantalla = true
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Esta función se encarga de añadir cualquier número de forma inteligente (CORREGIDA)
    private fun anadirNumero(numero: String) {
        val pantalla = binding.textViewResult


        if (limpiarPantalla || pantalla.text.toString() == "0") {
            pantalla.text = numero
            limpiarPantalla = false // ¡Importante! Desactivar justo después de usarla.
        } else {
            // Si no, simplemente lo añadimos al final.
            pantalla.append(numero)
        }
    }


    // FUNCIÓN PARA LIMPIAR
    private fun reiniciarCalculadora() {
        binding.textViewResult.text = "0"
        primerNumero = 0.0
        operacion = null
        limpiarPantalla = false
        Toast.makeText(this, "Calculadora reiniciada", Toast.LENGTH_SHORT).show()
    }
}