package it.skotlinyard.scan4students.utils

import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students

class SpinnerGetter(context: Context) {

    private val db = DbScan4Students.getInstance(context)

    fun getCollegesList(): ArrayList<String>{
        var list = db.universitaDao().getAllUniversitiesOrderByName()
        var array: ArrayList<String> = ArrayList()
        for(item in list)
            array.add(item.nome)
        return array
    }

    fun getAllProfs(): ArrayList<String>{
        var list = db.materieDao().getAllProf()
        var array: ArrayList<String> = ArrayList()
        array.add("")
        array.addAll(list)
        return array
    }

    fun getAllSubs(): ArrayList<String>{
        var list = db.materieDao().getAllSubjects()
        var array: ArrayList<String> = ArrayList()
        array.add("")
        array.addAll(list)
        return array
    }

    fun getAllFaculties(): ArrayList<String>{
        var list = db.materieDao().getAllFaculties()
        var array: ArrayList<String> = ArrayList()
        array.add("")
        array.addAll(list)
        return array
    }
}