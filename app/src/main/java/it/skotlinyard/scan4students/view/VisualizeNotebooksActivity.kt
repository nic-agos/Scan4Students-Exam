package it.skotlinyard.scan4students.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.skotlinyard.scan4students.model.Image
import it.skotlinyard.scan4students.model.ImageAdapter
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.databinding.ActivityVisualizeNotebooksBinding
import it.skotlinyard.scan4students.model.NotebookAdapter
import it.skotlinyard.scan4students.model.persistence.Quaderni
import it.skotlinyard.scan4students.utils.Session


class VisualizeNotebooksActivity : AppCompatActivity() {

    private var imageRecycler: RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var allNotebooks:MutableList<Quaderni>?=null //in realtà è array list di quaderni
    private lateinit var binding: ActivityVisualizeNotebooksBinding
    private var leftIcon: ImageView?=null
    private var rightIcon: ImageView?=null
    private var title: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizeNotebooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allNotebooks= Session.notebookSearchList

        leftIcon=findViewById(R.id.left_icon)
        leftIcon?.setOnClickListener{
            onBackPressed()
        }
        rightIcon=findViewById(R.id.right_icon)
        rightIcon?.setOnClickListener{
            val intent= Intent(this, CreateNotebookActivity::class.java) //Activity per inserimento nuovo quaderno
            startActivity(intent)
        }

        title=findViewById(R.id.toolbar_title)
        title?.setText(getString(R.string.Notebooks))

        imageRecycler=binding.notebookRecycler
        progressBar=binding.reyclerProgressBar

        imageRecycler?.layoutManager= GridLayoutManager(this,1)
        imageRecycler?.setHasFixedSize(true)

        progressBar?.visibility= View.VISIBLE
        //set adapter to recycler
        imageRecycler?.adapter= allNotebooks?.let { NotebookAdapter(this, it) }
        progressBar?.visibility= View.GONE

    }
}



