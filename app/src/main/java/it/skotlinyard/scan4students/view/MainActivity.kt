package it.skotlinyard.scan4students.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.controller.LoginController
import it.skotlinyard.scan4students.databinding.ActivityMainBinding
import it.skotlinyard.scan4students.databinding.ActivityMainBinding.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

//This is the login activity, set as the main activity. Every time the app is launched, users have to login
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        val controller = LoginController(this)
        var bool: Boolean? by Delegates.observable(null){property, oldValue, newValue ->
            if(newValue == true){ //login succesful, go to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else{ //login unsuccesful, try again
                Log.v("S4S", "Entrato nell'else nella MainActivity")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.registrationBtn.setOnClickListener{
            val intent= Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener {
            //get username and psw, send them to the controller to verify
            hideKeyboard()
            CoroutineScope(Dispatchers.IO).launch {
                Looper.prepare()
                Log.v("S4S", "entrato nella coroutine")
                bool = controller.verifyCredentials(binding.email.text.toString(),binding.psw.text.toString())
                Log.v("S4S", "valore di bool dopo il db $bool")
            }
        }
    }
}