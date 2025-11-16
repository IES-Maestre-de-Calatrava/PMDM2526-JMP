package model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abogado")
data class Abogado(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_abogado")
    val idAbogado:Long = 0,
    @ColumnInfo(name = "nombre")
    var nombre:String,
    @ColumnInfo(name = "telefono")
    var telefono:String,
    @ColumnInfo(name = "especialidad")
    var especialidad:String,
    @ColumnInfo(name = "biografia")
    var biografia: String): java.io.Serializable {

    }
