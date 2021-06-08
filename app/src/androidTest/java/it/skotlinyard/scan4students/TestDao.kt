package it.skotlinyard.scan4students

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import it.skotlinyard.scan4students.persistence.DbScan4Students
import it.skotlinyard.scan4students.persistence.Pagine
import it.skotlinyard.scan4students.persistence.Quaderni
import it.skotlinyard.scan4students.persistence.Studenti
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestDao {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val db = DbScan4Students.getInstance(context)

    @Test
    fun testGetAllNotebooksPages(){
        var pagine: MutableList<Pagine> = db.pagineDao().getAllNotebookPages(2)
        Log.w("S4S", "${pagine[0].path}")
    }
    /*
    @Test
    fun testNotebookInsert(){

        val notebook = Quaderni("quad di prova", 1, 0, "Private", "Italiano", "nicco2303","03/06/2021" )
        db.quaderniDao().insertNotebook(notebook)

    }


    @Test
    fun testGetAllNotebook(){
        var quaderni: LiveData<Quaderni> = db.quaderniDao().getAllNotebooks()
        Log.w("S4S", "${quaderni.value}")
    }


    @Test
    fun testStudentInsert(){
        val stud = Studenti("marco01", "password", "marco", "bello", "23/03/99", "M")
        db.studentiDao().insertStudent(stud)
    }

     */

}