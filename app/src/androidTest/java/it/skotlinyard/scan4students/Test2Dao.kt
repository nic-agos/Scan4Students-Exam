package it.skotlinyard.scan4students

import androidx.test.platform.app.InstrumentationRegistry
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Studenti
import org.junit.Test

class Test2Dao {

    @Test
    fun creaDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        var db = DbScan4Students.getInstance(context)

        var studente = Studenti("username3","psw","lollo","amo","09/06/1999","M","ingegneria")
        db.studentiDao().insertStudent(studente)


    }

}