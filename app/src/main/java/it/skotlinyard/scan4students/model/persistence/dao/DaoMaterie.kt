package it.skotlinyard.scan4students.model.persistence.dao

import androidx.room.Insert
import androidx.room.Query

import androidx.room.Dao
import it.skotlinyard.scan4students.model.persistence.Materie

@Dao
interface DaoMaterie {

    @Insert
    fun insertSubject(subject: Materie)

    @Query("DELETE FROM Materie WHERE indice = :indice")
    fun deleteSubject(indice: Int)

    @Query("SELECT * FROM Materie WHERE materia= :materia AND prof = :prof")
    fun getSubjectByProfAndSubject(materia: String, prof: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE materia = :materia")
    fun getSubjectBySubject(materia: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE prof = :prof")
    fun getSubjectByProf(prof: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE facolta = :faculty")
    fun getSubjectsByFaculty(faculty: String): MutableList<Materie>

    @Query("SELECT DISTINCT prof FROM Materie")
    fun getAllProf(): MutableList<String>

    @Query("SELECT DISTINCT materia FROM Materie")
    fun getAllSubjects(): MutableList<String>

    @Query("SELECT DISTINCT facolta FROM Materie")
    fun getAllFaculties(): MutableList<String>




}