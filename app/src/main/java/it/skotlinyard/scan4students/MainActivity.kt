package it.skotlinyard.scan4students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.skotlinyard.scan4students.databinding.ActivityMainBinding
import it.skotlinyard.scan4students.databinding.ActivityMainBinding.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }
}