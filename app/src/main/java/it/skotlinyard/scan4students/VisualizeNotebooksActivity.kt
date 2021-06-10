package it.skotlinyard.scan4students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.skotlinyard.scan4students.databinding.ActivityVisualizeNotebooksBinding

class VisualizeNotebooksActivity : AppCompatActivity() {

    private var imageRecycler: RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var allNotebooks:ArrayList<Image>?=null //in realtà è array list di quaderni
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
            val intent= Intent(this, HomeActivity::class.java) //Activity per inserimento nuovo quaderno
            startActivity(intent)
        }

        title=findViewById(R.id.toolbar_title)
        title?.setText(getString(R.string.Notebooks))

        imageRecycler=binding.imageRecycler
        progressBar=binding.reyclerProgressBar

        imageRecycler?.layoutManager= GridLayoutManager(this,1)
        imageRecycler?.setHasFixedSize(true)

        allNotebooks=ArrayList()
        if (allNotebooks!!.isEmpty())
        {
            TODO("Funzione che restituisce la lista dei quaderni dato il nome")
        }
    }
}