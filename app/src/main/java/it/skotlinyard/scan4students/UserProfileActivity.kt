package it.skotlinyard.scan4students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.skotlinyard.scan4students.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}