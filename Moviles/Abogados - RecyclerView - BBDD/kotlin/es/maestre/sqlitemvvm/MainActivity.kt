package es.maestre.sqlitemvvm

import adapter.AbogadoAdapter
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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import conexion.AppDatabase
import es.maestre.sqlitemvvm.databinding.ActivityMainBinding
import model.Abogado
import viewmodel.AbogadoViewModel

class MainActivity : AppCompatActivity() {


    lateinit var myAdapter:AbogadoAdapter
    lateinit var binding: ActivityMainBinding
    val viewmodel: AbogadoViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configurar barra de herramientas
        val toolbar: MaterialToolbar = binding.materialToolbar
        setSupportActionBar(toolbar)

        // Configuramos el RecyclerView
        initRecyclerView(viewmodel)

        //si cambian los datos, actualizar rv
        viewmodel.data.observe(this) { data ->
            myAdapter.updateData(data)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initRecyclerView(viewmodel: AbogadoViewModel) {
        val manager = LinearLayoutManager(this)
        binding.rvAbogados.layoutManager = manager
        myAdapter = AbogadoAdapter( mutableListOf())
        binding.rvAbogados.adapter = myAdapter

        //separador de elementos
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.rvAbogados.addItemDecoration(decoration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_add -> {
                val intent = Intent(this, AbogadoActivity::class.java)
                intent.putExtra("mode","NEW")
                startActivity(intent)
                true
            }
            R.id.menu_detalle -> {
                val selectedItem = myAdapter.getSelectedItem()
                if (selectedItem != null) {
                    val intent = Intent(this, AbogadoActivity::class.java)
                    intent.putExtra("mode","DETAIL")
                    intent.putExtra("abogado_data",selectedItem)
                    startActivity(intent)
                }
                true
            }
            R.id.menu_edicion -> {
                val selectedItem = myAdapter.getSelectedItem()
                if (selectedItem != null) {
                    val intent = Intent(this, AbogadoActivity::class.java)
                    intent.putExtra("mode","EDIT")
                    intent.putExtra("abogado_data",selectedItem)
                    startActivity(intent)
                }
                true
            }
            R.id.menu_eliminar -> {
                val selectedItem = myAdapter.getSelectedItem()
                if (selectedItem != null) {
                    viewmodel.delete(selectedItem)
                    myAdapter.notifyItemRemoved(myAdapter.getSelectedPosition())
                    myAdapter.notifyDataSetChanged()
                    Toast.makeText(this,"Se eliminó el abogado", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> false
        }
    }
}