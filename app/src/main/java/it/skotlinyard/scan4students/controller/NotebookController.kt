package it.skotlinyard.scan4students.controller

import android.content.Context
import android.util.Log
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Quaderni
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.Subject

class NotebookController(context: Context) {
    private val db = DbScan4Students.getInstance(context)


    fun searchNotebooksBySubject(subject: String): MutableList<Quaderni>{
        var resList = mutableListOf<Quaderni>()
        var quadList: MutableList<Quaderni>
        Log.v("S4S", "prima")

            Log.v("S4S", "dopo")
            var subList = db.materieDao().getSubjectBySubject(subject)
            Log.v("S4S", "dopo ancora")
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }

        return resList
    }

    fun searchNotebooksByProf(prof: String): MutableList<Quaderni>{
        var resList = mutableListOf<Quaderni>()
        var quadList: MutableList<Quaderni>
        CoroutineScope(Dispatchers.IO).launch {
            var subList = db.materieDao().getSubjectByProf(prof)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        }
        return resList
    }

    fun searchNotebooksByFaculty(faculty: String): MutableList<Quaderni>{
        var resList = mutableListOf<Quaderni>()
        var quadList: MutableList<Quaderni>
        CoroutineScope(Dispatchers.IO).launch {
            var subList = db.materieDao().getSubjectsByFaculty(faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        }
        return resList
    }

    fun searchNotebooksByProfAndSubject(prof: String, subject: String): MutableList<Quaderni>{
        var resList = mutableListOf<Quaderni>()
        var quadList: MutableList<Quaderni>
        CoroutineScope(Dispatchers.IO).launch {
            var subList = db.materieDao().getSubjectByProfAndSubject(prof, subject)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        }
        return resList
    }

    fun searchNotebooksByProfAndSubjectAndFaculty(prof: String, subject: String, faculty: String): MutableList<Quaderni>{
        var resList = mutableListOf<Quaderni>()
        var quadList: MutableList<Quaderni>
        CoroutineScope(Dispatchers.IO).launch {
            var subList = db.materieDao().getSubjectByProfAndSubjectAndFaculty(prof, subject, faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        }
        return resList
    }

    fun searchNotebooksByProfAndFaculty(prof: String, faculty: String): MutableList<Quaderni>{
        var resList = mutableListOf<Quaderni>()
        var quadList: MutableList<Quaderni>
        CoroutineScope(Dispatchers.IO).launch {
            var subList = db.materieDao().getSubjectByProfAndFaculty(prof, faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        }
        return resList
    }

    fun searchNotebooksBySubjectAndFaculty(subject: String, faculty: String): MutableList<Quaderni>{
        var resList = mutableListOf<Quaderni>()
        var quadList: MutableList<Quaderni>
        CoroutineScope(Dispatchers.IO).launch {
            var subList = db.materieDao().getSubjectBySubjectAndFaculty(subject, faculty)
            for (item in subList){
                quadList = db.quaderniDao().getPublicNotebooksBySubject(item.indice)
                if (!quadList.isEmpty()){
                    resList.addAll(quadList)
                }
            }
        }
        return resList
    }








}