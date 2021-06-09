package it.skotlinyard.scan4students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.skotlinyard.scan4students.databinding.ActivityMainBinding
import it.skotlinyard.scan4students.databinding.ActivityMainBinding.*
//This is the login activity, set as the main activity. Every time the app is launched, users have to login
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.registrationBtn.setOnClickListener{
            val intent= Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }
}