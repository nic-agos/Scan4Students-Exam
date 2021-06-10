package it.skotlinyard.scan4students

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.skotlinyard.scan4students.databinding.ActivityNotebookViewBinding

class NotebookViewActivity : AppCompatActivity() {

    private var imageRecycler: RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var allPictures:ArrayList<Image>?=null

    private lateinit var binding: ActivityNotebookViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotebookViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageRecycler=binding.imageRecycler
        progressBar=binding.reyclerProgressBar

        imageRecycler?.layoutManager= GridLayoutManager(this,3)
        imageRecycler?.setHasFixedSize(true)

        //Storage Permissions
        if(ContextCompat.checkSelfPermission(this@NotebookViewActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this@NotebookViewActivity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),101)
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