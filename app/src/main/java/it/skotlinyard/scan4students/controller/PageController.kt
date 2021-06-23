package it.skotlinyard.scan4students.controller

import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Pagine
import java.sql.SQLException

class PageController(context: Context) {
    private val db = DbScan4Students.getInstance(context)

    fun uploadPage(page: Pagine): Boolean{
        try{
            db.pagineDao().insertPage(page)
            return true
        }catch(e : SQLException){
            return false
        }
    }

    fun getLastPageNumber(quadIndex : Int): Int{
        try{
            return db.pagineDao().getLastPageNumber(quadIndex)
        }catch (e : SQLException){
            return -1
        }
    }

}