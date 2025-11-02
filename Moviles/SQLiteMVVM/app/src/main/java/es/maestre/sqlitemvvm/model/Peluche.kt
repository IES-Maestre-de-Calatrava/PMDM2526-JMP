package es.maestre.sqlitemvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peluche")
data class Peluche(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_peluche") var
    idPeluche: Int = 0,
    @ColumnInfo(name = "nombre") var nombre:String,
    @ColumnInfo(name = "marca") var marca:String,
    @ColumnInfo(name = "material") var material:String,
    @ColumnInfo(name = "biografia") var biografia: String): java.io.Serializable
