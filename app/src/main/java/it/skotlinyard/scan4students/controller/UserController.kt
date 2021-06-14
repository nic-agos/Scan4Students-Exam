package it.skotlinyard.scan4students.controller

import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Studenti

class UserController(context: Context, username: String) {
    private val db = DbScan4Students.getInstance(context)
    private val uname=username
    private val stud: Studenti = db.studentiDao().getStudent(username)

    fun getNotebooksCount(): Int{
        return db.quaderniDao().getPublicNotebooksByUser(uname).size
    }

    fun getFullName(): String{
       return stud.nome+stud.cognome
    }

    fun getBirthday(): String{
        return stud.dataDiNascita
    }

    fun getCollege(): String {
        return stud.universita
    }
    fun getStudent(): Studenti {
        return stud
    }


}