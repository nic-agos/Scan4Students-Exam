package it.skotlinyard.scan4students.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.transition.VisibilityAnimatorProvider
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.controller.NotebookController
import it.skotlinyard.scan4students.controller.UserController
import it.skotlinyard.scan4students.databinding.ActivityUserProfileBinding
import it.skotlinyard.scan4students.model.persistence.Quaderni
import it.skotlinyard.scan4students.utils.Session
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var uname: String
    private lateinit var ncontroller :NotebookController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        ncontroller = NotebookController(this)

        uname = intent.getStringExtra("user").toString()

        binding.usernameField.text = uname

        var birthday: String by Delegates.observable(""){property, oldValue, newValue ->
            binding.birthdayText.text=newValue
        }
        var fullname: String by Delegates.observable(""){property, oldValue, newValue ->
            binding.nameSurname.text=newValue
        }
        var notebookCount: String by Delegates.observable(""){property, oldValue, newValue ->
            binding.notebookLabel.text = newValue
        }
        var collegeName: String by Delegates.observable(""){property, oldValue, newValue ->
            binding.collegeText.text=newValue
        }
        var personalNotebookList: MutableList<Quaderni>?by Delegates.observable(null) { property, oldValue, newValue ->
            if (newValue==null)
                Toast.makeText(this, R.string.search_error, Toast.LENGTH_SHORT).show()
            else{
                Session.notebookSearchList = newValue
                val intent = Intent(this, VisualizeNotebooksActivity::class.java)
                if(uname==Session.getCurrUsername())
                    intent.putExtra("add_btn_toolbar",true)
                else
                    intent.putExtra("add_btn_toolbar",false)
                startActivity(intent)
            }
        }

        val ucontroller =  UserController(this)

        CoroutineScope(Dispatchers.IO).launch {
            val stud = ucontroller.getStudent(uname)
            if (stud != null) {
                fullname = stud.nome+" "+stud.cognome
                birthday = stud.dataDiNascita
                collegeName = stud.universita
                notebookCount = ucontroller.getNotebooksCount(uname).toString()
            }
        }

        binding.statsBtn.setOnClickListener{
            val intent = Intent(this, StatsActivity::class.java)
            intent.putExtra("user",uname)
            startActivity(intent)
        }
        binding.notebooksBtn.setOnClickListener {
            if (Session.getCurrUsername() == uname) {
                CoroutineScope(Dispatchers.IO).launch {
                    personalNotebookList = ncontroller.getAllByUser(Session.getCurrUsername())
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    personalNotebookList = ncontroller.getPubNotebookByUser(uname)
                }
            }
        }
        binding.homeB.setOnClickListener{
            Log.v("S4S","click home btn userprofile")
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("prev","user")
            startActivity(intent)
        }

        }
    override fun onBackPressed() {
        if(Session.getCurrUsername()==uname){
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("prev","user")
            startActivity(intent)
        }
        else{
            super.onBackPressed()
        }

    }
    }


