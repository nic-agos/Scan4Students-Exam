package it.skotlinyard.scan4students.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoQuaderni {
    @Insert
    fun insertNotebook(notebook: Quaderni)

    @Query("DELETE FROM Quaderni WHERE indice = :notebookId")
    fun deleteNotebook(notebookId: Int)

    @Query("SELECT * FROM Quaderni")
    fun getAllNotebooks(): MutableList<Quaderni>

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

}