package it.skotlinyard.scan4students

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import it.skotlinyard.scan4students.model.persistence.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestDao {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val db = DbScan4Students.getInstance(context)


    // test DaoUniversita
    @Test
    fun testGetAllUniversitiesOrderByName(){
        var uni: MutableList<Universita> = db.universitaDao().getAllUniversitiesOrderByName()
        for (u in uni){
            Log.v("S4S", "${u.nome}" )
        }

    }

    @Test
    fun testGetAllUniversitiesOrderByRegion(){
        var uni: MutableList<Universita> = db.universitaDao().getAllUniversitiesOrderByRegion()
        for (u in uni){
            Log.v("S4S", "${u.nome}" )
        }

    }

    @Test
    fun testGetAllUniversities(){
        var uni: MutableList<Universita> = db.universitaDao().getAllUniversities()
        for (u in uni){
            Log.v("S4S", "${u.nome}" )
        }

    }

    // test DaoMaterie
    @Test
    fun testInsertSubject(){
        var mat = Materie("Ingegneria", "Analisi II", "Perfetti", 6, 2, 1)
        db.materieDao().insertSubject(mat)

    }

    @Test
    fun testDeleteSubject(){
        db.materieDao().deleteSubject(1)
    }

    @Test
    fun testGetSubjectByProfAndSubject(){
        var subList: MutableList<Materie> = db.materieDao().getSubjectByProfAndSubject("Analisi I", "Tarantello")
        for (s in subList) Log.v("S4S", "${s.indice}")
    }

    @Test
    fun testGetSubjectBySubject(){
        var subList: MutableList<Materie> = db.materieDao().getSubjectBySubject("Analisi II")
        for (s in subList){
            Log.v("S4S", "${s.prof}")
        }
    }

    @Test
    fun testGetSubjectByProf(){
        var subList: MutableList<Materie> = db.materieDao().getSubjectByProf("Tarantello")
        for (s in subList){
            Log.v("S4S", "${s.materia}")
        }
    }

    @Test
    fun testGetSubjectsByFaculty(){
        var subList: MutableList<Materie> = db.materieDao().getSubjectsByFaculty("Ingegneria")
        Log.v("S4S", "${subList.size}")
        for (s in subList){
            Log.v("S4S", "${s.materia}")
        }
    }

    @Test
    fun testGetAllProf(){
        var profList: MutableList<String> = db.materieDao().getAllProf()
        Log.v("S4S", "${profList.size}")
        for (s in profList){
            Log.v("S4S", "${s}")
        }
    }

    @Test
    fun testGetAllSubjects(){
        var subList: MutableList<String> = db.materieDao().getAllSubjects()
        Log.v("S4S", "${subList.size}")
        for (s in subList){
            Log.v("S4S", "${s}")
        }
    }

    @Test
    fun testGetAllFaculties(){
        var facList: MutableList<String> = db.materieDao().getAllFaculties()
        Log.v("S4S", "${facList.size}")
        for (s in facList){
            Log.v("S4S", "${s}")
        }
    }

    // test DaoStudenti
    @Test
    fun testInsertStudent(){
       var stud = Studenti ("nicco23","password", "Niccolò", "Agostinelli", "23/03/1999", "M", "Ingegneria")
        db.studentiDao().insertStudent(stud)
    }

    @Test
    fun testLogin(){
        var stud: Studenti = db.studentiDao().login("nicco23","password")
        Log.v("S4S", "${stud != null}")
    }

    @Test
    fun testDeleteStudent(){
        db.studentiDao().deleteStudent("lollo")
    }

    @Test
    fun testGetAllStudents(){
        var studList: MutableList<Studenti> = db.studentiDao().getAllStudents()
        for (s in studList){
            Log.v("S4S", "${s.nome}")
        }
    }

    @Test
    fun testGetStudent(){
        var stud: Studenti = db.studentiDao().getStudent("nicco23")
        Log.v("S4S", "${stud.nome}")
    }

    // test DaoQuaderni
    @Test
    fun testInsertNotebook(){
        var notebook = Quaderni("quad di prova priv", 2, 0, "PRIVATE", "Italiano", "nicco23", "03/06/2021")
        db.quaderniDao().insertNotebook(notebook)
    }

    @Test
    fun testDeleteNotebook(){
        db.quaderniDao().deleteNotebook(1)
    }

    @Test
    fun testGetAllNotebooks(){
        var quadList: MutableList<Quaderni> = db.quaderniDao().getAllNotebooks()
        for (s in quadList){
            Log.v("S4S", "${s.titolo}")
        }
    }

    @Test
    fun testGetNotebookByIndex(){
        var quad: Quaderni = db.quaderniDao().getNotebookByIndex(2)
        Log.v("S4S", "${quad.titolo}")
    }

    @Test
    fun testGetPublicNotebooksBySubject(){
        var quadList: MutableList<Quaderni> = db.quaderniDao().getPublicNotebooksBySubject(2)
        for (s in quadList){
            Log.v("S4S", "${s.titolo}")
        }
    }

    @Test
    fun testGetPublicNotebooksByUser(){
        var quadList: MutableList<Quaderni> = db.quaderniDao().getPublicNotebooksByUser("nicco23")
        for (s in quadList){
            Log.v("S4S", "${s.titolo}")
        }
    }

    @Test
    fun testGetMyPrivateNotebooks(){
        var quadList: MutableList<Quaderni> = db.quaderniDao().getMyPrivateNotebooks("nicco23")
        for (s in quadList){
            Log.v("S4S", "${s.titolo}")
        }
    }

    @Test
    fun testUpdateNotebookPageNumber(){
        db.quaderniDao().updateNotebookPageNumber(3,2)
    }

    // test DaoPagine
    @Test
    fun testInsertPage(){
        var pag: Pagine = Pagine(2, 5, "Path")
        db.pagineDao().insertPage(pag)
    }

    @Test
    fun testDeletePage(){
        db.pagineDao().deletePage(2)
    }

    @Test
    fun testGetAllNotebookPages(){
        var pagList: MutableList<Pagine> = db.pagineDao().getAllNotebookPages(2)
        for(p in pagList){
            Log.v("S4S", "${p.numPagina}")
        }
    }

    @Test
    fun testGetLatestUploadedPage(){
        var last: Int = db.pagineDao().getLatestUploadedPage()
        Log.v("S4S","$last")
    }

}