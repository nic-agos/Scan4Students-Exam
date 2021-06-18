package it.skotlinyard.scan4students

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import it.skotlinyard.scan4students.controller.NotebookController
import org.junit.Test
import it.skotlinyard.scan4students.utils.Stats
import java.time.Month

class TestController {

    val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val stats = Stats(context)

    @Test
    fun testStats(){
        var list = stats.getStudentNotebooks("nicco2303")
        Log.v("S4S", "entrato in test")
        Log.v("S4S", "list size: ${list.size}")
        var map = stats.getStats(list)
        Log.v("S4S", "$map")
    }
}