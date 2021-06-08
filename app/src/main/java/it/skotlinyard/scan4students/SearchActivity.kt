package it.skotlinyard.scan4students

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import it.skotlinyard.scan4students.databinding.ActivityCameraBinding
import it.skotlinyard.scan4students.databinding.ActivitySearchBinding

class SearchActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioNotebook.setOnClickListener {
            binding.collegeSpinner.visibility= View.VISIBLE
            binding.facultySpinner.visibility= View.VISIBLE
            binding.professorSpinner.visibility= View.VISIBLE
            binding.subjectSpinner.visibility= View.VISIBLE
            binding.collegeText.visibility= View.VISIBLE
            binding.facultyText2.visibility= View.VISIBLE
            binding.professorText.visibility= View.VISIBLE
            binding.subjectText.visibility= View.VISIBLE
            binding.searchBar.visibility=View.GONE
            binding.searchButton.visibility=View.GONE
        }
        binding.radioUser.setOnClickListener {
            binding.collegeSpinner.visibility= View.GONE
            binding.facultySpinner.visibility= View.GONE
            binding.professorSpinner.visibility= View.GONE
            binding.subjectSpinner.visibility= View.GONE
            binding.collegeText.visibility= View.GONE
            binding.facultyText2.visibility= View.GONE
            binding.professorText.visibility= View.GONE
            binding.subjectText.visibility= View.GONE
            binding.searchBar.visibility=View.VISIBLE
            binding.searchButton.visibility=View.VISIBLE
        }
    }
}