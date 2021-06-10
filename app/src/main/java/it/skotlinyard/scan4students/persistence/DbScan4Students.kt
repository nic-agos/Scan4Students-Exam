package it.skotlinyard.scan4students.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Quaderni::class, Materie::class, Studenti::class, Pagine::class], version = 1, exportSchema = true)
abstract class DbScan4Students : RoomDatabase() {
    companion object {
        private var db: DbScan4Students? = null // Singleton
        fun getInstance(context: Context): DbScan4Students {
            if (db == null)
                db = Room.databaseBuilder(
                        context.applicationContext,
                        DbScan4Students::class.java,
                        "Scan4Students_v.1.2.db"

                )
                       .createFromAsset("database/Scan4Students_v.1.2.db")
            .build()
            return db as DbScan4Students
        }
    }

    abstract fun pagineDao(): DaoPagine
    abstract fun quaderniDao(): DaoQuaderni
    abstract fun studentiDao(): DaoStudenti
    abstract fun materieDAO():DaoMaterie
}

