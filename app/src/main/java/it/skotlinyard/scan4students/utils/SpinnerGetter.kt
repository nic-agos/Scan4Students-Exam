package it.skotlinyard.scan4students.utils

import android.content.Context
import it.skotlinyard.scan4students.model.persistence.DbScan4Students

class SpinnerGetter(context: Context) {

    private val db = DbScan4Students.getInstance(context)

    fun getCollegesList(): ArrayList<String>{

        val list = db.universitaDao().getAllUniversitiesOrderByName()
        val array: ArrayList<String> = ArrayList()
        for(item in list)
            array.add(item.nome)
        return array
    }

    fun getAllProfs(): ArrayList<String>{
        val list = db.materieDao().getAllProf()
        val array: ArrayList<String> = ArrayList()
        array.add("")
        array.addAll(list)
        return array
    }

    fun getAllSubs(): ArrayList<String>{
        val list = db.materieDao().getAllSubjects()
        val array: ArrayList<String> = ArrayList()
        array.add("")
        array.addAll(list)
        return array
    }

    fun getAllFaculties(): ArrayList<String>{
        val list = db.materieDao().getAllFaculties()
        val array: ArrayList<String> = ArrayList()
        array.add("")
        array.addAll(list)
        return array
    }
}