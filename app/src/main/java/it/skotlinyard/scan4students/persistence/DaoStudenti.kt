package it.skotlinyard.scan4students.persistence

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface DaoStudenti {

    @Insert
    fun insertStudent(student: Studenti)
}