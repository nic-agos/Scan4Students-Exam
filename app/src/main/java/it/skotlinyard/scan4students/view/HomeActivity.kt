package it.skotlinyard.scan4students.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.controller.NotebookController
import it.skotlinyard.scan4students.databinding.ActivityHomeBinding
import it.skotlinyard.scan4students.model.persistence.Quaderni
import it.skotlinyard.scan4students.utils.Session
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onBackPressed() {
        val prev = intent.getStringExtra("prev")
        if(prev=="login"){
        Session.setLogged(false)
        Session.setCurrUsername("")
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        }
        else{
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.setTitle(R.string.app_name)
        setContentView(binding.root)

        val ncontroller = NotebookController(this)

        var personalNotebookList: MutableList<Quaderni>? by Delegates.observable(null) { property, oldValue, newValue ->
            if (newValue.isNullOrEmpty())
                Toast.makeText(this, R.string.search_error, Toast.LENGTH_SHORT).show()
            else {
                Session.notebookSearchList = newValue
                val intent = Intent(this, VisualizeNotebooksActivity::class.java)
                startActivity(intent)
            }
        }

        binding.searchButtonH.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        binding.profileButton.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            intent.putExtra("user", Session.getCurrUsername())
            startActivity(intent)
        }
        binding.myNotebookButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                personalNotebookList = ncontroller.getAllByUser(Session.getCurrUsername())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about ->
                Toast.makeText(this, R.string.app_name,
                    Toast.LENGTH_LONG).show()
            R.id.settings -> {
                Toast.makeText(this, "Work in Progress",
                    Toast.LENGTH_LONG).show()
            }
            R.id.logout->{
                Session.setLogged(false)
                Session.setCurrUsername("")
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}