package es.maestre.holaandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.maestre.holaandroid.databinding.ActivityInitBinding


class InitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}