package it.skotlinyard.scan4students.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoPagine {

    @Insert
    fun insertPage(pagina: Pagine)

    @Query("DELETE FROM Pagine WHERE indice = :index")
    fun deletePage(index: Int)

    @Query("SELECT * FROM Pagine WHERE quaderno = :notebook ORDER BY numPagina")
    fun getAllNotebookPages(notebook: Int) : MutableList<Pagine>


}