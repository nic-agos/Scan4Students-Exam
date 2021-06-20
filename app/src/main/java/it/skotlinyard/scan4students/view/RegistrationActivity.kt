package it.skotlinyard.scan4students.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.controller.RegistrationController
import it.skotlinyard.scan4students.databinding.ActivityRegistrationBinding
import it.skotlinyard.scan4students.model.persistence.Studenti
import it.skotlinyard.scan4students.utils.Hashing
import it.skotlinyard.scan4students.utils.SpinnerGetter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var gender: String
    private lateinit var controller: RegistrationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        supportActionBar?.setTitle(R.string.registration)
        setContentView(binding.root)

        val context = this.applicationContext
        val getter = SpinnerGetter(context)
        controller = RegistrationController(context)

        var colleges: ArrayList<String> by Delegates.observable(ArrayList()){ property, oldValue, newValue ->
            val adapter = ArrayAdapter(context,android.R.layout.simple_expandable_list_item_1,newValue)
            binding.spinner.setAdapter(adapter)
        }
        CoroutineScope(Dispatchers.IO).launch{
            Looper.prepare()
            colleges = getter.getCollegesList()
        }

        var bool: Boolean? by Delegates.observable(null){property, oldValue, newValue ->
            Log.v("S4S","bool has changed value: $newValue. Previously was: $oldValue")
            if (newValue == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.internal_error, Toast.LENGTH_LONG).show()
            }
        }

        binding.regBtn.setOnClickListener {
            //psw and confirm psw are the same?
            if (binding.name.text.toString().isBlank() || binding.surname.text.toString()
                    .isBlank() ||
                binding.username.text.toString().isBlank() || binding.pswEntry.text.toString()
                    .isBlank() ||
                binding.pswConfirm.text.toString()
                    .isBlank() || binding.spinner.selectedItem.toString().isBlank()
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
                        binding.birthday.month +1,
                        binding.birthday.year
                    )
                    Log.v("S4S", "birthday: ${birthday[0]} ${birthday[1]} ${birthday[2]}")
                val student = syntaxControl(
                    binding.name.text.toString(),
                    binding.surname.text.toString(),
                    binding.username.text.toString(),
                    binding.pswEntry.text.toString(),
                    birthday,
                    binding.spinner.selectedItem.toString(),
                    gender
                )
                    CoroutineScope(Dispatchers.IO).launch {
                        Log.v("S4S","Thread launched for user registration")
                        bool = controller.regUser(student)
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
        gender: String): Studenti {
        val hashUtil = Hashing()
        val formattedBirthday = birthday[0].toString()+"-"+birthday[1].toString()+"-"+birthday[2].toString()
        Log.v("S4S", "formattedBirthday: ${formattedBirthday}")
        val pswHashed = hashUtil.md5(pswEntry)

        val stud = Studenti(username, pswHashed, name, surname, formattedBirthday, gender, college)
        return stud
    }

}