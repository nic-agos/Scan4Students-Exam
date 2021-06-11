package it.skotlinyard.scan4students.model.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.skotlinyard.scan4students.model.persistence.Studenti

@Dao
interface DaoStudenti {

    @Insert
    fun insertStudent(student: Studenti)

    @Query("DELETE FROM Studenti WHERE username = :username")
    fun deleteStudent(username: String)

    @Query("SELECT * FROM Studenti WHERE username = :username AND password = :password")
    fun login(username : String,password : String): Studenti

    @Query("SELECT * FROM Studenti")
    fun getAllStudents(): MutableList<Studenti>

    @Query("SELECT * FROM Studenti WHERE username =:username")
    fun getStudent(username: String): Studenti



}