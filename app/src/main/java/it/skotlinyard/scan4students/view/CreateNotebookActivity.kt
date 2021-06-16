package it.skotlinyard.scan4students.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import it.skotlinyard.scan4students.controller.NotebookController
import it.skotlinyard.scan4students.controller.RegistrationController
import it.skotlinyard.scan4students.databinding.ActivityCreateNotebookBinding
import it.skotlinyard.scan4students.utils.SpinnerGetter

class CreateNotebookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNotebookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNotebookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getter = SpinnerGetter(this)

        val professorArray = getter.getAllProfs()
        val subjectArray = getter.getAllSubs()

        val profAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,professorArray)
        val subAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,subjectArray)

        profAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.profSpinner.adapter=profAdapter
        binding.subSpinner.adapter=subAdapter



    }
}