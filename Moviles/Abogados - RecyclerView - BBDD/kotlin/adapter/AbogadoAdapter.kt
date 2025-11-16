package adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.maestre.sqlitemvvm.R
import model.Abogado

class AbogadoAdapter(private val abogados: List<Abogado>): RecyclerView.Adapter<AbogadoViewHolder>() {
    private var posSelectedItem: Int = -1
    private var data: List<Abogado>
    init {
        data = abogados
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbogadoViewHolder {
        // Inflamos el layout de cada elemento
        val layoutInflater = LayoutInflater.from(parent.context)
        return AbogadoViewHolder(layoutInflater.inflate(R.layout.item_lista, parent, false))
    }

    fun updateData(newData: List<Abogado>) {
        this.data = newData
        notifyItemChanged(0, data.size - 1)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getSelectedPosition(): Int {
        return posSelectedItem
    }

    fun getSelectedItem(): Abogado? {
        var item: Abogado? = null
        if (posSelectedItem != -1) {
            item = data[posSelectedItem]
        }
        return item
    }

    override fun onBindViewHolder(holder: AbogadoViewHolder, position: Int) {
        // Inicializamos la lista
        val abogado = data[position]
        holder.nombre.text = abogado.nombre
        holder.especialidad.text = abogado.especialidad

        // al hacer clic, el fondo del ítem cambia de color
        // seleccionamos el dato
        holder.itemView.setOnClickListener {
            if (holder.isBackgroundColorChanged) {
                //deseleccionamos el item
                holder.itemView.setBackgroundColor(Color.TRANSPARENT)
                holder.isBackgroundColorChanged = false
                if (posSelectedItem == holder.absoluteAdapterPosition) {
                    posSelectedItem = -1
                }
            } else if (posSelectedItem == -1) {
                    //seleccionamos el item
                    posSelectedItem = holder.absoluteAdapterPosition
                    holder.itemView.setBackgroundColor(Color.LTGRAY)
                    holder.isBackgroundColorChanged = true
            }
        }
    }
}