package es.maestre.sqlitemvvm.conexion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.maestre.sqlitemvvm.model.Peluche

@Database(entities = [Peluche::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pelucheDAO(): PelucheDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "peluches.db3"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}