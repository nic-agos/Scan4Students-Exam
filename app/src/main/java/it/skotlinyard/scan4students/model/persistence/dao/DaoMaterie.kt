package it.skotlinyard.scan4students.model.persistence.dao

import androidx.room.Insert
import androidx.room.Query

import androidx.room.Dao
import it.skotlinyard.scan4students.model.persistence.Materie

@Dao
interface DaoMaterie {

    @Insert
    fun insertSubject(subject: Materie): Long

    @Query("DELETE FROM Materie WHERE indice = :indice")
    fun deleteSubject(indice: Int)

    @Query("SELECT * FROM Materie WHERE materia= :materia AND prof = :prof")
    fun getSubjectByProfAndSubject(prof: String, materia: String): Materie

    @Query("SELECT * FROM Materie WHERE materia= :materia AND prof = :prof AND facolta = :facolta")
    fun getSubjectByProfAndSubjectAndFaculty(prof: String, materia: String, facolta: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE facolta = :facolta AND prof = :prof")
    fun getSubjectByProfAndFaculty(prof: String, facolta: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE facolta = :facolta AND materia = :materia")
    fun getSubjectBySubjectAndFaculty(materia: String, facolta: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE materia = :materia")
    fun getSubjectBySubject(materia: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE prof = :prof")
    fun getSubjectByProf(prof: String): MutableList<Materie>

    @Query("SELECT * FROM Materie WHERE facolta = :faculty")
    fun getSubjectsByFaculty(faculty: String): MutableList<Materie>

    @Query("SELECT DISTINCT prof FROM Materie ORDER BY prof")
    fun getAllProf(): MutableList<String>

    @Query("SELECT DISTINCT materia FROM Materie ORDER BY materia")
    fun getAllSubjects(): MutableList<String>

    @Query("SELECT DISTINCT facolta FROM Materie ORDER BY facolta")
    fun getAllFaculties(): MutableList<String>




}