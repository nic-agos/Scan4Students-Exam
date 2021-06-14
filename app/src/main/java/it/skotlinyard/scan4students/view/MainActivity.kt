package it.skotlinyard.scan4students.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import it.skotlinyard.scan4students.controller.LoginController
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

        val controller = LoginController(this)

        binding.registrationBtn.setOnClickListener{
            val intent= Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener {
            //get username and psw, send them to the controller to verify
            val bool = controller.verifyCredentials(binding.email.text.toString(),binding.psw.text.toString())

            if(bool){ //login succesful, go to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else{ //login unsuccesful, try again
                Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_LONG).show()
            }
        }
    }
}