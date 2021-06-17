package it.skotlinyard.scan4students.controller


import android.content.Context
import android.util.Log
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Studenti
import it.skotlinyard.scan4students.utils.Hashing
import it.skotlinyard.scan4students.utils.Session
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.SQLException


//TODO class to be tested
class LoginController(context: Context) {

    private var db = DbScan4Students.getInstance(context)
    private var hashUtil = Hashing()

    fun verifyCredentials(uname: String, psw: String): Boolean{
        var stud: Studenti
        //hashing the password
        val hashed = hashUtil.md5(psw)
        //using dao function to login
        try{
            stud = db.studentiDao().login(uname,hashed)
            if (stud != null){
                Session.setCurrUsername(stud.username)
                Session.setLogged(true)
                return true
            }else
                return false

        }catch (e: SQLException){
            Log.v("S4S", "Entrato in eccezione in verifyCredentials")
            Session.setLogged(false)
            Session.setCurrUsername("")
            return false
        }
    }
}