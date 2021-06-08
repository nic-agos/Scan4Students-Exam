package it.skotlinyard.scan4students.persistence

import androidx.room.Insert
import androidx.room.Query

interface DaoMaterie {

    @Insert
    fun insertSubject(subject: Materie)

    @Query("")
    fun deleteSubject(materia: String)
}