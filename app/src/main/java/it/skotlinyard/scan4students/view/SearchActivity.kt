package it.skotlinyard.scan4students.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.controller.SearchController
import it.skotlinyard.scan4students.databinding.ActivitySearchBinding
import it.skotlinyard.scan4students.model.persistence.Studenti
import it.skotlinyard.scan4students.utils.Session
import it.skotlinyard.scan4students.utils.SpinnerGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class SearchActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val getter = SpinnerGetter(this)
        val controller = SearchController(this)

        var professorArray: ArrayList<String> by Delegates.observable(ArrayList()){property, oldValue, newValue ->
            val profAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,newValue)
            profAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.professorSpinner.adapter = profAdapter
        }
        var subjectArray: ArrayList<String> by Delegates.observable(ArrayList()) { property, oldValue, newValue ->
            val subAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,newValue)
            subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.subjectSpinner.adapter = subAdapter
        }
        var facultyArray: ArrayList<String> by Delegates.observable(ArrayList()) { property, oldValue, newValue ->
            val facAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,newValue)
            facAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.facultySpinner.adapter = facAdapter
        }

        var resultUser: Studenti? by Delegates.observable(null){property, oldValue, newValue ->
            if(newValue!=null){
            val intent= Intent(this, UserProfileActivity::class.java)
            intent.putExtra("user", newValue.username)
            startActivity(intent)
            }
            else
                Toast.makeText(this, R.string.search_error, Toast.LENGTH_SHORT).show()
        }

        CoroutineScope(Dispatchers.IO).launch{
            professorArray = getter.getAllProfs()
            subjectArray = getter.getAllSubs()
            facultyArray = getter.getAllFaculties()
        }

        binding.radioNotebook.setOnClickListener {
            binding.facultySpinner.visibility= View.VISIBLE
            binding.professorSpinner.visibility= View.VISIBLE
            binding.subjectSpinner.visibility= View.VISIBLE
            binding.facultyText2.visibility= View.VISIBLE
            binding.professorText.visibility= View.VISIBLE
            binding.subjectText.visibility= View.VISIBLE
            binding.searchButtonNote.visibility=View.VISIBLE
            binding.textInputLayout.visibility=View.GONE
            binding.searchButtonUser.visibility=View.GONE
        }
        binding.radioUser.setOnClickListener {
            binding.facultySpinner.visibility= View.GONE
            binding.professorSpinner.visibility= View.GONE
            binding.subjectSpinner.visibility= View.GONE
            binding.facultyText2.visibility= View.GONE
            binding.professorText.visibility= View.GONE
            binding.subjectText.visibility= View.GONE
            binding.searchButtonNote.visibility=View.GONE
            binding.textInputLayout.visibility=View.VISIBLE
            binding.searchButtonUser.visibility=View.VISIBLE
        }

        binding.searchButtonNote.setOnClickListener {
            if(binding.subjectSpinner.selectedItem.toString().isBlank()&&
                binding.facultySpinner.selectedItem.toString().isBlank()&&
                binding.professorSpinner.selectedItem.toString().isBlank())


            if(binding.subjectSpinner.selectedItem.toString().isNotBlank()&&
                binding.facultySpinner.selectedItem.toString().isBlank()&&
                binding.professorSpinner.selectedItem.toString().isBlank()) {

            }
            if(binding.subjectSpinner.selectedItem.toString().isBlank()&&
                binding.facultySpinner.selectedItem.toString().isNotBlank()&&
                binding.professorSpinner.selectedItem.toString().isBlank()){

            }
            if(binding.subjectSpinner.selectedItem.toString().isBlank()&&
                binding.facultySpinner.selectedItem.toString().isBlank()&&
                binding.professorSpinner.selectedItem.toString().isNotBlank()){

            }
            if(binding.subjectSpinner.selectedItem.toString().isNotBlank()&&
                binding.facultySpinner.selectedItem.toString().isNotBlank()&&
                binding.professorSpinner.selectedItem.toString().isBlank()){

            }
            if(binding.subjectSpinner.selectedItem.toString().isNotBlank()&&
                binding.facultySpinner.selectedItem.toString().isBlank()&&
                binding.professorSpinner.selectedItem.toString().isNotBlank()) {

            }
            if(binding.subjectSpinner.selectedItem.toString().isBlank()&&
                binding.facultySpinner.selectedItem.toString().isNotBlank()&&
                binding.professorSpinner.selectedItem.toString().isNotBlank()) {

            }
            if(binding.subjectSpinner.selectedItem.toString().isNotBlank()&&
                binding.facultySpinner.selectedItem.toString().isNotBlank()&&
                binding.professorSpinner.selectedItem.toString().isNotBlank()){

            }
        }

        binding.searchButtonUser.setOnClickListener {
            val username = binding.searchBar.text.toString()
            CoroutineScope(Dispatchers.IO).launch{
                resultUser = controller.searchUserByUsername(username)
            }
        }
    }
}