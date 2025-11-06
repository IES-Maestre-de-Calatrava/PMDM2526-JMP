package es.maestre.sqlitemvvm.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.maestre.sqlitemvvm.R
import es.maestre.sqlitemvvm.model.Peluche

class PelucheAdapter(
    private var peluches: List<Peluche>,
    private val onClick: (Peluche) -> Unit
) : RecyclerView.Adapter<PelucheAdapter.PelucheViewHolder>() {

    inner class PelucheViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvMarca: TextView = view.findViewById(R.id.tvMarca)
        val tvMaterial: TextView = view.findViewById(R.id.tvMaterial)
        val tvBiografia: TextView = view.findViewById(R.id.tvBiografia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PelucheViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_peluche, parent, false)
        return PelucheViewHolder(view)
    }

    override fun onBindViewHolder(holder: PelucheViewHolder, position: Int) {
        val peluche = peluches[position]
        holder.tvNombre.text = peluche.nombre
        holder.tvMarca.text = peluche.marca
        holder.tvMaterial.text = peluche.material
        holder.tvBiografia.text = peluche.biografia

        holder.itemView.setOnClickListener { onClick(peluche) }
    }

    override fun getItemCount(): Int = peluches.size

    fun updateData(newData: List<Peluche>) {
        peluches = newData
        notifyDataSetChanged()
    }
}