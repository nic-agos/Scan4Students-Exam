package it.skotlinyard.scan4students.model.persistence.dao

import android.icu.util.UniversalTimeScale
import androidx.room.Dao
import androidx.room.Query
import it.skotlinyard.scan4students.model.persistence.Universita

@Dao
interface DaoUniversita {

    @Query("SELECT * FROM Universita ORDER BY nome")
    fun getAllUniversitiesOrderByName(): MutableList<Universita>

    @Query("SELECT * FROM Universita ORDER BY regione")
    fun getAllUniversitiesOrderByRegion(): MutableList<Universita>

    @Query("SELECT * FROM Universita")
    fun getAllUniversities(): MutableList<Universita>

    @Query("SELECT * FROM Universita WHERE nome = :name")
    fun getUniversityByName(name: String): Universita
}