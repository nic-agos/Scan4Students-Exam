package it.skotlinyard.scan4students

import android.app.Application
import android.app.Instrumentation
import android.content.Context

import it.skotlinyard.scan4students.persistence.DbScan4Students
import it.skotlinyard.scan4students.persistence.Quaderni
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.lifecycle.AndroidViewModel
import it.skotlinyard.scan4students.ViewModel.QuaderniVM
import it.skotlinyard.scan4students.persistence.DaoQuaderni
import org.junit.Test

public class DaoTest(){

    @Test
    fun testQuadInsert() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val notebook = Quaderni("quad di prova", 1, 0, "Private", "Italiano", "nicco2303","03/06/2021" )
        val db = DbScan4Students.getInstance(context)


    }


}