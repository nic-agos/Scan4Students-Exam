package it.skotlinyard.scan4students.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//Materie::class, Pagine::class, Quaderni::class, Studenti::class, Quaderni::class
@Database(entities = [Studenti::class], version = 1, exportSchema = true)
abstract class DbScan4Students : RoomDatabase() {
    companion object {
        private var db: DbScan4Students? = null // Singleton
        fun getInstance(context: Context): DbScan4Students {
            if (db == null)
                db = Room.databaseBuilder(
                        context.applicationContext,
                        DbScan4Students::class.java,
                        "Scan4Students.db"
                )
                        .createFromAsset("database/Scan4Students.db")
                        .build()
            return db as DbScan4Students
        }
    }

    abstract fun studentiDao(): DaoStudenti
    /*
    abstract fun quaderniDao(): DaoQuaderni
    abstract fun materieDAO():DaoMaterie
    abstract fun pagineDAO(): DaoPagine

     */
}

