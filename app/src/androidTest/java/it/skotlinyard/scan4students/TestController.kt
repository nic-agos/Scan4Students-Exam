package it.skotlinyard.scan4students

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import it.skotlinyard.scan4students.controller.NotebookController
import org.junit.Test

class TestController {

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testSearchNotebooksBySubject(){
        var contr = NotebookController(context)
        var list = contr.searchNotebooksBySubject("Analisi II")
        //Log.v("S4S", "${list.size}")
        for (i in list){
            //Log.v("S4S", "${i.titolo}")
        }
    }
}