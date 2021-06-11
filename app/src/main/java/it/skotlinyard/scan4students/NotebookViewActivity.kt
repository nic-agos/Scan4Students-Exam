package it.skotlinyard.scan4students

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.skotlinyard.scan4students.databinding.ActivityNotebookViewBinding
import it.skotlinyard.scan4students.utils.FolderWorker

class NotebookViewActivity : AppCompatActivity() {

    private var imageRecycler: RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var allPictures:ArrayList<Image>?=null
    private var leftIcon: ImageView?=null
    private var rightIcon: ImageView?=null
    private var title: TextView?=null

    private lateinit var binding: ActivityNotebookViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotebookViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        leftIcon=findViewById(R.id.left_icon)
        leftIcon?.setOnClickListener{
            val intent= Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        rightIcon=findViewById(R.id.right_icon)
        rightIcon?.setOnClickListener{
            val intent= Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        val notebookTitle= intent.getStringExtra("Nome")
        val notebookSubject= intent.getStringExtra("Materia")
        title=findViewById(R.id.toolbar_title)
        title?.setText(notebookTitle+"\n"+notebookSubject)

        imageRecycler=binding.imageRecycler
        progressBar=binding.reyclerProgressBar

        imageRecycler?.layoutManager= GridLayoutManager(this,3)
        imageRecycler?.setHasFixedSize(true)

        //Storage Permissions
        if(ContextCompat.checkSelfPermission(this@NotebookViewActivity, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this@NotebookViewActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),101)
        }
        allPictures= ArrayList()
        if(allPictures!!.isEmpty())
        {
            progressBar?.visibility= View.VISIBLE
            //get all Images from storage
            allPictures=getAllImages()
            //set adapter to recycler
            imageRecycler?.adapter=ImageAdapter(this, allPictures!!)
            progressBar?.visibility= View.GONE
        }
    }

    private fun getAllImages(): ArrayList<Image> {
        val images = ArrayList<Image>()


        val gestore = FolderWorker()
        val listOfFiles = gestore.getListFileFromDirectory("")
        if (listOfFiles != null) {
            listOfFiles.forEach { i ->
                Toast.makeText(baseContext, i.name, Toast.LENGTH_SHORT).show()
                val image = Image()
                image.imagePath = i.path
                image.imageName = i.name
                images.add(image)
            }
        }

        return images
    }
}