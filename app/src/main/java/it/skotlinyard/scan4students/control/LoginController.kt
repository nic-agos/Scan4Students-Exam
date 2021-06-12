package it.skotlinyard.scan4students.control


import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Studenti
import it.skotlinyard.scan4students.utils.Hashing
import it.skotlinyard.scan4students.utils.Session


//TODO class to be tested
class LoginController(context: Context) {

    private var db = DbScan4Students.getInstance(context)
    private lateinit var stud: Studenti
    private var hashUtil = Hashing()
    fun verifyCredentials(uname: String, psw: String): Boolean{
        //hashing the password
        val hashed = hashUtil.md5(psw)
        //using dao function to login
        stud = db.studentiDao().login(uname,hashed)
        //Is login gone fine?
        if(stud.username==uname){
            Session.setCurrUsername(stud.username)
            Session.setLogged(true)
            return true
        }
        else{
            Session.setLogged(false)
            Session.setCurrUsername("")
            return false
        }
    }
}