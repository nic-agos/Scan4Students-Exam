package it.skotlinyard.scan4students.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.skotlinyard.scan4students.databinding.ActivityHomeBinding
import it.skotlinyard.scan4students.utils.Session

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onBackPressed() {
        Session.setLogged(false)
        Session.setCurrUsername("")
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.searchButtonH.setOnClickListener{
            val intent= Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        binding.profileButton.setOnClickListener{
            val intent= Intent(this, UserProfileActivity::class.java)
            intent.putExtra("user", Session.getCurrUsername())
            startActivity(intent)
        }
        binding.myNotebookButton.setOnClickListener{
        //TODO add notebook view and intent here.
        val intent= Intent(this, CreateNotebookActivity::class.java)
            startActivity(intent)
         //   Toast.makeText(this, "work in progress", Toast.LENGTH_LONG).show()
        }
    }


}