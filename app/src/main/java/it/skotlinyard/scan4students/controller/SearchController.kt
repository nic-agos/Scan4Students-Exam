package it.skotlinyard.scan4students.controller

import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Quaderni
import it.skotlinyard.scan4students.model.persistence.Studenti
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchController(context: Context) {
    private val db = DbScan4Students.getInstance(context)

    fun searchNotebooksBySubject(subject: String): MutableList<Quaderni>{
        lateinit var resList: MutableList<Quaderni>
        var quadList: MutableList<Quaderni>
            val subList = db.materieDao().getSubjectBySubject(subject)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        return resList
    }

    fun searchNotebooksByProf(prof: String): MutableList<Quaderni>{
        lateinit var resList: MutableList<Quaderni>
        var quadList: MutableList<Quaderni>
            val subList = db.materieDao().getSubjectByProf(prof)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        return resList
    }

    fun searchNotebooksByFaculty(faculty: String): MutableList<Quaderni>{
        lateinit var resList: MutableList<Quaderni>
        var quadList: MutableList<Quaderni>
            val subList = db.materieDao().getSubjectsByFaculty(faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        return resList
    }

    fun searchNotebooksByProfAndSubject(prof: String, subject: String): MutableList<Quaderni>{
        lateinit var resList: MutableList<Quaderni>
        val quadList: MutableList<Quaderni>
            val materia = db.materieDao().getSubjectByProfAndSubject(prof, subject)
            quadList = db.quaderniDao().getPublicNotebooksBySubject(materia.indice)
            if (!quadList.isEmpty()){
                resList.addAll(quadList)
            }
        return resList
    }

    fun searchNotebooksByProfAndSubjectAndFaculty(prof: String, subject: String, faculty: String): MutableList<Quaderni>{
        lateinit var resList: MutableList<Quaderni>
        var quadList: MutableList<Quaderni>
           val subList = db.materieDao().getSubjectByProfAndSubjectAndFaculty(prof, subject, faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        return resList
    }

    fun searchNotebooksByProfAndFaculty(prof: String, faculty: String): MutableList<Quaderni>{
        lateinit var resList: MutableList<Quaderni>
        var quadList: MutableList<Quaderni>
            val subList = db.materieDao().getSubjectByProfAndFaculty(prof, faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        return resList
    }

    fun searchNotebooksBySubjectAndFaculty(subject: String, faculty: String): MutableList<Quaderni>{
        lateinit var resList: MutableList<Quaderni>
        var quadList: MutableList<Quaderni>
            val subList = db.materieDao().getSubjectBySubjectAndFaculty(subject, faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        return resList
    }

    fun searchUserByUsername(username: String): Studenti{
        val res = db.studentiDao().getStudent(username)
        return res
    }
    fun getAllNotebooks(): MutableList<Quaderni>{
        val res = db.quaderniDao().getAllNotebooks()
        return res
    }
}