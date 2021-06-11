package it.skotlinyard.scan4students.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.skotlinyard.scan4students.databinding.ActivityCreateNotebookBinding

class CreateNotebookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNotebookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNotebookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}