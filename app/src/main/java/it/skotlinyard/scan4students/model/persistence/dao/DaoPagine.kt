package it.skotlinyard.scan4students.model.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import it.skotlinyard.scan4students.model.persistence.Pagine

@Dao
interface DaoPagine {

    @Insert
    fun insertPage(pagina: Pagine): Long

    @Query("DELETE FROM Pagine WHERE indice = :index")
    fun deletePage(index: Int)

    @Query("SELECT * FROM Pagine WHERE quaderno = :notebook ORDER BY numPagina")
    fun getAllNotebookPages(notebook: Int) : MutableList<Pagine>

    @Query("SELECT MAX(indice) FROM Pagine")
    fun getLatestUploadedPage(): Int

    @Query("SELECT MAX(numPagina) FROM Pagine WHERE indice = :index")
    fun getLastPageNumber(index: Int): Int

}