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

    @Query("SELECT * FROM Quaderni")
    fun getAllNotebooks(): LiveData<Quaderni>

    @Query("SELECT * FROM Quaderni WHERE materia = :subject AND visibilita = 'PUBLIC'")
    fun getOtherNotebooksBySubject(subject: Int) : MutableList<Quaderni>

    @Query("SELECT * FROM Quaderni WHERE studente = :username AND visibilita = 'PUBLIC'")
    fun getPublicNotebooksByUser(username: String) : MutableList<Quaderni>

    @Query("SELECT * FROM Quaderni WHERE studente = :username AND visibilita = 'PRIVATE'")
    fun getMyPrivateNotebooks(username: String) : MutableList<Quaderni>

    @Query("UPDATE Quaderni SET numeroPagine = :pageNumb WHERE `index` = :notebookId")
    fun updateNotebookPageNumber(pageNumb: Int, notebookId: Int)

    @Query("DELETE FROM Quaderni WHERE `index` = :notebookId")
    fun deleteNotebook(notebookId: Int)


}