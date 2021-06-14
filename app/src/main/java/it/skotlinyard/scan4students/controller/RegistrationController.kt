package it.skotlinyard.scan4students.controller

import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Studenti
import it.skotlinyard.scan4students.utils.Hashing

class RegistrationController(context: Context) {
    private var db = DbScan4Students.getInstance(context)
    private lateinit var stud: Studenti
    private var hashUtil = Hashing()

    fun regUser(name: String,
                        surname: String,
                        username: String,
                        pswEntry: String,
                        birthday: String,
                        college: String,
                        gender: String)
            : Boolean{
        stud.nome=name
        stud.cognome=surname
        stud.dataDiNascita=birthday
        stud.password = hashUtil.md5(pswEntry)
        stud.username=username
        stud.universita=college
        stud.sesso=gender

        TODO("Add try catch to get exception")
        db.studentiDao().insertStudent(stud)

        return true
    }
}