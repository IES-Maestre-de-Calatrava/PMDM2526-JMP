package es.maestre.sqlitemvvm.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import es.maestre.sqlitemvvm.R
import es.maestre.sqlitemvvm.databinding.ItemPelucheBinding
import es.maestre.sqlitemvvm.model.Peluche

class PelucheAdapter(
    // Recibimos el LiveData y la Activity (que es el LifecycleOwner)
    private var peluchesLiveData: LiveData<List<Peluche>>,
    private var activity: AppCompatActivity
) : RecyclerView.Adapter<PelucheAdapter.PelucheViewHolder>() {

    // Lista interna que se actualizará cuando el LiveData cambie
    private var peluches: List<Peluche> = emptyList()

    init {
        // Cada vez que los datos en el ViewModel cambien, este bloque se ejecutará.
        peluchesLiveData.observe(activity) { listaPeluches ->
            this.peluches = listaPeluches ?: emptyList() // Actualizamos la lista interna
            notifyDataSetChanged() // Notificamos al RecyclerView que se redibuje
        }
    }

    class PelucheViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPelucheBinding.bind(view)
        val nombre: TextView = binding.tvNombre
        val marca: TextView = binding.tvMarca
        val material: TextView = binding.tvMaterial
        val bio: TextView = binding.tvBiografia
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PelucheViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_peluche, parent, false)
        return PelucheViewHolder(view)
    }

    override fun onBindViewHolder(holder: PelucheViewHolder, position: Int) {
        val peluche = peluches[position] // Usamos la lista interna
        holder.nombre.text = peluche.nombre
        holder.marca.text = peluche.marca
        holder.material.text = peluche.material
        holder.bio.text = peluche.biografia
    }

    override fun getItemCount(): Int = peluches.size 
}
