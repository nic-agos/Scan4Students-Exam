package it.skotlinyard.scan4students.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

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