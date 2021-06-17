package it.skotlinyard.scan4students.controller

import android.content.Context
import android.renderscript.Int3
import android.util.Log
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Materie
import it.skotlinyard.scan4students.model.persistence.Quaderni
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.SQLException
import javax.security.auth.Subject

class NotebookController(context: Context) {
    private val db = DbScan4Students.getInstance(context)

    fun uploadNotebook(quad: Quaderni): Boolean{
        try {
            db.quaderniDao().insertNotebook(quad)
            Log.v("S4S","Notebook upload good")
            return true
        }catch (e: SQLException){
            Log.v("S4S","Exception in NotebookController.uploadNotebook")
            return false
        }
    }

    fun getSubIDByNameAndProf(sub: String,prof: String): Int{
        var materia: Materie
        try {
            materia = db.materieDao().getSubjectByProfAndSubject(prof,sub)
            Log.v("S4S","sub search good in getSubByID")
            return materia.indice
        }catch (e: SQLException){
            Log.v("S4S","Exception in NotebookController.uploadNotebook")
            return -1
        }
    }
}