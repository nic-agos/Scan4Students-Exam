package it.skotlinyard.scan4students.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import it.skotlinyard.scan4students.controller.UserController
import it.skotlinyard.scan4students.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var uname: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uname = intent.getStringExtra("user").toString()

        val controller =  UserController(this,uname)

        binding.usernameField.text = uname
        binding.birthdayText.text = controller.getBirthday()
        binding.nameSurname.text = controller.getFullName()
        binding.notebookLabel.text = controller.getNotebooksCount(uname).toString()

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

    }
}