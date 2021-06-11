package it.skotlinyard.scan4students.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.databinding.ActivityRegistrationBinding

// Username, password, nome, cognome, data di nascita, sesso, anno di iscrizione all'università

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var gender: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        supportActionBar?.setTitle(R.string.registration)
        setContentView(binding.root)

        binding.regBtn.setOnClickListener{
            if(binding.male.isChecked)
                gender="M"
            else if(binding.female.isChecked)
                gender="F"
            val birthday = arrayOf(binding.birthday.dayOfMonth,binding.birthday.month,binding.birthday.year)
            syntaxControl(binding.name.toString(),binding.surname.toString(), binding.username.toString(),
                binding.pswEntry.toString(),binding.pswConfirm.toString(),birthday,binding.collegeYear.toString().toInt(),
                gender)

            //after registration go back to login
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun syntaxControl(name: String,surname: String,username: String,pswEntry: String,
                              pswConfirm: String,birthday: Array<Int>, collegeYear: Int, gender: String){
        //TODO
    }

}