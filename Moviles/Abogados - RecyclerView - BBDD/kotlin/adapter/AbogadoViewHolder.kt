package adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.maestre.sqlitemvvm.databinding.ItemListaBinding

class AbogadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemListaBinding.bind(itemView)
    val nombre: TextView = binding.nombre
    val especialidad: TextView = binding.especialidad

    var isBackgroundColorChanged = false
}