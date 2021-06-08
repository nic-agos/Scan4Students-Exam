package it.skotlinyard.scan4students.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import it.skotlinyard.scan4students.persistence.DbScan4Students
import it.skotlinyard.scan4students.persistence.Quaderni
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuaderniVM(application: Application) : AndroidViewModel(application){
    /*
    fun insertNotebook(notebook: Quaderni){
        val db = DbScan4Students.getInstance(getApplication())
        CoroutineScope(Dispatchers.IO).launch {
            val res = db.quaderniDao().insertNotebook(notebook)
            Log.w("S4S", "query result: $res")
        }
    }

     */
}