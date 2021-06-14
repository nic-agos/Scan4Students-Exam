package it.skotlinyard.scan4students.controller

import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Quaderni
import javax.security.auth.Subject

class NotebookController(context: Context) {
    private val db = DbScan4Students.getInstance(context)

    fun searchNotebook(professor: String, subject: String, faculty: String){
        var all = db.quaderniDao().getAllNotebooks()
        var resutlt: MutableList<Quaderni>
        for (item in all)
            if (item.visibilita=="PRIV")
                all.remove(item)


    }
}