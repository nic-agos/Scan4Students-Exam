package it.skotlinyard.scan4students.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.controller.NotebookController
import it.skotlinyard.scan4students.controller.RegistrationController
import it.skotlinyard.scan4students.databinding.ActivityCreateNotebookBinding
import it.skotlinyard.scan4students.model.persistence.Quaderni
import it.skotlinyard.scan4students.utils.FolderWorker
import it.skotlinyard.scan4students.utils.Session
import it.skotlinyard.scan4students.utils.SpinnerGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class CreateNotebookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNotebookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNotebookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val context = this.applicationContext
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse("${LocalDate.now()}")
        val currentDate = date.format(formatter)
        val controller = NotebookController(this)

        val getter = SpinnerGetter(this)

        var professorArray: ArrayList<String> by Delegates.observable(ArrayList()){property, oldValue, newValue ->
            val profAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,newValue)
            profAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.profSpinner.adapter=profAdapter
        }
        var subjectArray: ArrayList<String> by Delegates.observable(ArrayList()){property, oldValue, newValue ->
            val subjectAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,newValue)
            subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.subSpinner.adapter=subjectAdapter
        }
        var bool: Boolean? by Delegates.observable(null) { property, oldValue, newValue ->
            if(newValue==true){
                val intent= Intent(this, UserProfileActivity::class.java)
                intent.putExtra("user", Session.getCurrUsername())
                startActivity(intent)
            }
            else
                Toast.makeText(this, R.string.internal_error, Toast.LENGTH_SHORT).show()
        }

        CoroutineScope(Dispatchers.IO).launch{
            professorArray = getter.getAllProfs()
            subjectArray = getter.getAllSubs()
        }

        binding.createBtn.setOnClickListener {

            var visibility = String()
            if(binding.privateRadio.isChecked)
                visibility = "PRIVATE"
            else if(binding.publicRadio.isChecked)
                visibility="PUBLIC"

            if(binding.entryTitle.text.toString().isBlank()||
                binding.subSpinner.selectedItem.toString()==""||
                binding.subSpinner.selectedItem.toString()=="")
                Toast.makeText(this, R.string.empty_entry, Toast.LENGTH_SHORT).show()
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    val subID = controller.getSubIDByNameAndProf(binding.subSpinner.selectedItem.toString(),
                    binding.profSpinner.selectedItem.toString())
                    Log.v("S4S", "Prima della upload")
                    if (subID != -1){
                        val quad = Quaderni(binding.entryTitle.text.toString(),
                            subID,0, visibility,"ITA", Session.getCurrUsername(),currentDate.toString())
                        bool = controller.uploadNotebook(quad)
                        val gestore = FolderWorker()
                        gestore.createNewSubDirectory("/"+Session.getCurrUsername(),quad.titolo)
                    }else{
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(context, R.string.prof_sub_err, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}