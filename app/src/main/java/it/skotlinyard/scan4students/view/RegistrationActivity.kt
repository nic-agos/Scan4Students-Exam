package it.skotlinyard.scan4students.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.controller.RegistrationController
import it.skotlinyard.scan4students.databinding.ActivityRegistrationBinding

// Username, password, nome, cognome, data di nascita, sesso, anno di iscrizione all'università

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var gender: String
    private lateinit var controller: RegistrationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        supportActionBar?.setTitle(R.string.registration)
        setContentView(binding.root)

        controller = RegistrationController(this)

        binding.regBtn.setOnClickListener {
            //psw and confirm psw are the same?


            if (binding.name.text.toString().isBlank() || binding.surname.text.toString()
                    .isBlank() ||
                binding.username.text.toString().isBlank() || binding.pswEntry.text.toString()
                    .isBlank() ||
                binding.pswConfirm.text.toString()
                    .isBlank() || binding.college.text.toString().isBlank()
            ) {
                Toast.makeText(this, R.string.empty_entry, Toast.LENGTH_LONG).show()
            }

            else {
                if (!(binding.pswConfirm.text.toString()==binding.pswEntry.text.toString()))
                    Toast.makeText(this, R.string.psw_error, Toast.LENGTH_LONG).show()
                else{
                if (binding.male.isChecked)
                    gender = "M"
                else if (binding.female.isChecked)
                    gender = "F"
                val birthday =
                    arrayOf(
                        binding.birthday.dayOfMonth,
                        binding.birthday.month,
                        binding.birthday.year
                    )
                val bool = syntaxControl(
                    binding.name.text.toString(),
                    binding.surname.text.toString(),
                    binding.username.text.toString(),
                    binding.pswEntry.text.toString(),
                    birthday,
                    binding.college.text.toString(),
                    gender
                )
                if (bool) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, R.string.internal_error, Toast.LENGTH_LONG).show()
                }
            }
            }
        }

    }

    private fun syntaxControl(
        name: String,
        surname: String,
        username: String,
        pswEntry: String,
        birthday: Array<Int>,
        college:String,
        gender: String)
        : Boolean {
        val formattedBirthday = birthday[0].toString()+"/"+birthday[1].toString()+"/"+birthday[2].toString()

        return controller.regUser(name,surname,username,pswEntry,formattedBirthday,college,gender)
    }

}