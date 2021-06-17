package it.skotlinyard.scan4students.controller

import android.content.Context
import android.database.SQLException
import android.util.Log
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Studenti
import it.skotlinyard.scan4students.utils.Hashing

class RegistrationController(context: Context) {
    private var db = DbScan4Students.getInstance(context)

    fun regUser(stud: Studenti): Boolean{

        try {
            var res = db.studentiDao().insertStudent(stud)
            Log.v("S4S", "$res")
            if (res.toInt() != 1){
                Log.v("S4S", "Username duplicate")
                return false
            }
            return true
        }catch (e: SQLException){
            return false
        }
    }
}