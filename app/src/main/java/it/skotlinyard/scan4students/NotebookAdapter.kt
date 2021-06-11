package it.skotlinyard.scan4students

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.skotlinyard.scan4students.persistence.Quaderni

class NotebookAdapter(private var context: Context, private var notebooksList: ArrayList<Quaderni>) :
    RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder>() {

    class NotebookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView?=null
        init {
            image=itemView.findViewById(R.id.row_image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_recycler_notebook,parent,false)
        return NotebookViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotebookViewHolder, position: Int) {
        val currentNotebook=notebooksList[position]

        TODO("Associare immagine a quaderno")

        Glide.with(context)
            .load(currentNotebook.imagePath)
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