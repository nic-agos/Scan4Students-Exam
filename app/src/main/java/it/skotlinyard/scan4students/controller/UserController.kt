package it.skotlinyard.scan4students.controller

import android.content.Context
import android.util.Log
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Studenti
import it.skotlinyard.scan4students.model.persistence.Universita
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.SQLException

class UserController(context: Context) {
    private val db = DbScan4Students.getInstance(context)


    fun getStudent(username: String): Studenti?{
        try {
            var studente = db.studentiDao().getStudent(username)
            if(studente != null){
                Log.v("S4S", "Utente trovato")
                return studente

            }else{
                Log.v("S4S", "Utente non trovato")
                return null
            }

        }catch (e: SQLException){
            Log.v("S4S", "Entrato in eccezione UserController.getStudent")
            return null
        }

    }


    fun getNotebooksCount(username: String): Int{
        CoroutineScope(Dispatchers.IO).launch {
            var count = db.quaderniDao().getPublicNotebooksByUser(username).size
        }
        return 0
    }

    fun getFullName(): String{
       return stud.nome + stud.cognome
    }

    fun getBirthday(): String{
        return stud.dataDiNascita
    }

    fun getCollegeName(): String {
        return stud.universita
    }

    /*
    fun getCollegeAcronym(uName: String): String {
        var university: Universita
        CoroutineScope(Dispatchers.IO).launch {
           university = db.universitaDao().getUniversityByName(uName)
        }
        return university.acronimo
    }

     */



}