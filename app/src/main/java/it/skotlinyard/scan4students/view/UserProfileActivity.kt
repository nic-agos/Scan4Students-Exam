package it.skotlinyard.scan4students.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.skotlinyard.scan4students.controller.UserController
import it.skotlinyard.scan4students.databinding.ActivityUserProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var uname: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

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
        val controller =  UserController(this)

        CoroutineScope(Dispatchers.IO).launch {
            val stud = controller.getStudent(uname)
            if (stud != null) {
                fullname = stud.nome+" "+stud.cognome
                birthday = stud.dataDiNascita
                collegeName = stud.universita
                notebookCount = controller.getNotebooksCount(uname).toString()
            }
        }

        binding.statsBtn.setOnClickListener{
            val intent = Intent(this, StatsActivity::class.java)
            intent.putExtra("user",uname)
            startActivity(intent)
        }
        binding.notebooksBtn.setOnClickListener{
         /*   val intent = Intent(this, QUALE ACTIVITY ??? ::class.java)
            intent.putExtra("user",uname)
            startActivity(intent)*/
        }
        binding.homeB.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("prev","user")
            startActivity(intent)
        }
    }
}