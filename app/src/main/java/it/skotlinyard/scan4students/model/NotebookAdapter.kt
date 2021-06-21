package it.skotlinyard.scan4students.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.model.persistence.Quaderni
import it.skotlinyard.scan4students.view.NotebookViewActivity

class NotebookAdapter(private var context: Context, private var notebooksList: MutableList<Quaderni>) :
    RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder>() {

    class NotebookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView?=null
        init {
            image=itemView.findViewById(R.id.notebookImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_recycler_notebook,parent,false)
        return NotebookViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotebookViewHolder, position: Int) {
        val currentNotebook=notebooksList[position]

        Glide.with(context)
            .load(R.drawable.an1_ing)
            .apply(RequestOptions().centerCrop())
            .into(holder.image!!)

        holder.image?.setOnClickListener {
            val intent= Intent(context, NotebookViewActivity::class.java)
            intent.putExtra("Nome",currentNotebook.titolo)
            intent.putExtra("Materia",currentNotebook.materia)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return notebooksList.size
    }


}



