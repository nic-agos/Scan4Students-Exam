package it.skotlinyard.scan4students.model.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.skotlinyard.scan4students.model.persistence.Quaderni

@Dao
interface DaoQuaderni {
    @Insert
    fun insertNotebook(notebook: Quaderni): Long

    @Query("DELETE FROM Quaderni WHERE indice = :notebookId")
    fun deleteNotebook(notebookId: Int)

    @Query("SELECT * FROM Quaderni")
    fun getAllNotebooks(): MutableList<Quaderni>

    @Query("SELECT * FROM Quaderni WHERE visibilita='PUBLIC'")
    fun getAllPublicNotebooks():MutableList<Quaderni>

    @Query("SELECT * FROM Quaderni WHERE indice = :notebookId")
    fun getNotebookByIndex(notebookId: Int): Quaderni

    @Query("SELECT * FROM Quaderni WHERE materia = :subject AND visibilita = 'PUBLIC'")
    fun getPublicNotebooksBySubject(subject: Int) : MutableList<Quaderni>

    @Query("SELECT * FROM Quaderni WHERE studente = :username AND visibilita = 'PUBLIC'")
    fun getPublicNotebooksByUser(username: String) : MutableList<Quaderni>

    @Query("SELECT * FROM Quaderni WHERE studente = :username AND visibilita = 'PRIVATE'")
    fun getMyPrivateNotebooks(username: String) : MutableList<Quaderni>

    @Query("UPDATE Quaderni SET numeroPagine = :pageNumb WHERE indice = :notebookId")
    fun updateNotebookPageNumber(pageNumb: Int, notebookId: Int)

    @Query("SELECT * FROM Quaderni WHERE studente = :username" )
    fun getAllNotebooksByUser(username: String): MutableList<Quaderni>
}