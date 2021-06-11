package it.skotlinyard.scan4students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.skotlinyard.scan4students.databinding.ActivityVisualizeNotebooksBinding
import it.skotlinyard.scan4students.persistence.Quaderni

class VisualizeNotebooksActivity : AppCompatActivity() {

    private var notebookRecycler: RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var allNotebooks:ArrayList<Quaderni>?=null //in realtà è array list di quaderni
    private lateinit var binding: ActivityVisualizeNotebooksBinding
    private var leftIcon: ImageView?=null
    private var rightIcon: ImageView?=null
    private var title: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizeNotebooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        leftIcon=findViewById(R.id.left_icon)
        leftIcon?.setOnClickListener{
            val intent= Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        rightIcon=findViewById(R.id.right_icon)
        rightIcon?.setOnClickListener{
            val intent= Intent(this, CreateNotebookActivity::class.java)
            startActivity(intent)
        }

        title=findViewById(R.id.toolbar_title)
        title?.setText(getString(R.string.Notebooks))

        notebookRecycler=binding.notebookRecycler
        progressBar=binding.reyclerProgressBar

        notebookRecycler?.layoutManager= GridLayoutManager(this,1)
        notebookRecycler?.setHasFixedSize(true)

        allNotebooks=ArrayList()
        if (allNotebooks!!.isEmpty())
        {

            progressBar?.visibility= View.VISIBLE
            //get all NOtebooks from storage
            TODO("Lista di quaderni")
            //set adapter to recycler
            notebookRecycler?.adapter= NotebookAdapter(this, allNotebooks!!)
            progressBar?.visibility= View.GONE
        }
    }
}