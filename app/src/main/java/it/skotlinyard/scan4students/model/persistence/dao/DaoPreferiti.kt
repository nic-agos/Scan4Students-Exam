package it.skotlinyard.scan4students.model.persistence.dao

import android.os.FileObserver.DELETE
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.skotlinyard.scan4students.model.persistence.Preferiti

@Dao
interface DaoPreferiti {

    @Insert
    fun addFavourite(favourite: Preferiti)

    @Query("DELETE FROM Preferiti WHERE utente = :user AND quadPref = :favNotebook")
    fun removeFavourite(user: String, favNotebook: Int)

    @Query("SELECT * FROM Preferiti WHERE utente = :user")
    fun getAllFavouritesByUser(user: String): MutableList<Preferiti>
}