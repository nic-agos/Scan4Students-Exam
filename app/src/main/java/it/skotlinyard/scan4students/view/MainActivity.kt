package it.skotlinyard.scan4students.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import it.skotlinyard.scan4students.controller.LoginController
import it.skotlinyard.scan4students.databinding.ActivityMainBinding
import it.skotlinyard.scan4students.databinding.ActivityMainBinding.inflate
import it.skotlinyard.scan4students.model.persistence.DbScan4Students
import it.skotlinyard.scan4students.model.persistence.Pagine
import it.skotlinyard.scan4students.utils.FolderWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
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

        prepareFolders()
        copyAssets()

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
            intent.putExtra("prev","login")
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener {
            //get username and psw, send them to the controller to verify
            hideKeyboard()
            CoroutineScope(Dispatchers.IO).launch {
                Log.v("S4S", "entrato nella coroutine")
                bool = controller.verifyCredentials(binding.email.text.toString(),binding.psw.text.toString())
                Log.v("S4S", "valore di bool dopo il db $bool")
            }
        }
    }


    private fun prepareFolders() {



        val gestoreFiles = FolderWorker()
        println("CREIAMO STO FILE SYSTEM")
        /*val file = File("file:///android_asset/samplepictures/1_Analisi _I(2)")
        println(file.name)
        file.copyTo(File(gestoreFiles.startPath))*/

        val myCoroutineScope= CoroutineScope(Dispatchers.IO)
        myCoroutineScope.launch(Dispatchers.IO) {
            val db = DbScan4Students.getInstance(baseContext)
            val utenti = db.studentiDao().getAllStudents()
            utenti.forEach{
                studente ->
                gestoreFiles.createNewDirectory(studente.username)
                val quaderni = db.quaderniDao().getPublicNotebooksByUser(studente.username)
                quaderni.forEach{
                    quaderno->
                    gestoreFiles.createNewSubDirectory("/"+quaderno.studente,quaderno.titolo)
                }
            }
        }
    }

    private fun copyAssets() {
        val assetManager = assets
        var `in`: InputStream? = null
        var out: OutputStream? = null
        var outFile = File("/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Nicco2303/Quad di Analisi I", "1_Analisi_I(1).jpg")
        var pathLocal = ""



        try{

            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Nicco2303/Quad di Analisi I"
            `in` = assetManager.open("samplepictures/1_Analisi_ I(1).jpg")
            outFile = File(pathLocal, "1_Analisi_I(1).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)


            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Nicco2303/Quad di Analisi I"
            `in` = assetManager.open("samplepictures/1_Analisi_ I(1).jpg")
            outFile = File(pathLocal, "1_Analisi_I(2).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)


            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Nicco2303/Quad di Geometria"
            `in` = assetManager.open("samplepictures/2_Geometria(1).jpg")
            outFile = File(pathLocal, "2_Geometria(1).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)


            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Nicco2303/Quad di Geometria"
            `in` = assetManager.open("samplepictures/2_Geometria(2).jpg")
            outFile = File(pathLocal, "2_Geometria(2).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)

            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Anarion/Quad di Diritto Privato"
            `in` = assetManager.open("samplepictures/3_Diritto_Privato.jpg")
            outFile = File(pathLocal, "3_Diritto_Privato(1).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)

            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Antius/Quad di Diritto Civile"
            `in` = assetManager.open("samplepictures/4_Diritto_Civile.jpg")
            outFile = File(pathLocal, "4_Diritto_Civile(1).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)

            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/BLBlue/Quad di Economia Aziendale"
            `in` = assetManager.open("samplepictures/5_Economia_Aziendale.jpg")
            outFile = File(pathLocal, "5_Economia_Aziendale(1).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)

            pathLocal = "/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App/Lorenz99/Quad di Diritto Ecclesiastico"
            `in` = assetManager.open("samplepictures/6_Diritto_Ecclesiastico.jpg")
            outFile = File(pathLocal, "6_Diritto_Ecclesiastico(1).jpg")
            out = FileOutputStream(outFile)
            copyFile(`in`, out)


        }catch(e: IOException) {
            println("S4S: ERRORE INSERIMENTO PAGINE DEFAULT")
        }finally {
            if (`in` != null) {
                try {
                    `in`.close()
                } catch (e: IOException) {
                }
            }
            if (out != null) {
                try {
                    out.close()
                } catch (e: IOException) {
                }
            }
        }
    }

    /*private fun copyAssets() {
        val assetManager = assets
        var files: Array<String>? = null
        try {
            files = assetManager.list("samplepictures") //MODIFICA
        } catch (e: IOException) {
        }
        if (files != null) for (filename in files) {
            var `in`: InputStream? = null
            var out: OutputStream? = null
            try {
                `in` = assetManager.open("samplepictures/" + filename)
                val outFile = File("/storage/emulated/0/Android/media/it.skotlinyard.scan4students/CameraX App", filename)
                out = FileOutputStream(outFile)
                copyFile(`in`, out)
            } catch (e: IOException) {
                Log.e("tag", "Failed to copy asset file: $filename", e)
            } finally {
                if (`in` != null) {
                    try {
                        `in`.close()
                    } catch (e: IOException) {
                    }
                }
                if (out != null) {
                    try {
                        out.close()
                    } catch (e: IOException) {
                    }
                }
            }
        }
    }*/

    @Throws(IOException::class)
    private fun copyFile(`in`: InputStream, out: OutputStream) {
        val buffer = ByteArray(1024)
        var read: Int
        while (`in`.read(buffer).also { read = it } != -1) {
            out.write(buffer, 0, read)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}