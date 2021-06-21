package it.skotlinyard.scan4students.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.skotlinyard.scan4students.R
import it.skotlinyard.scan4students.R.drawable.*
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

        val v:View=LayoutInflater.from(context).inflate(R.layout.row_recycler_notebook,null)

        var titolo: TextView = v.findViewById <TextView>(R.id.contenutoTitolo)
        titolo.setText(currentNotebook.titolo)

        Log.v("S4S","Titolo del quaderno: ${titolo.text}")

        var lingua: TextView = v.findViewById <TextView>(R.id.contenutoLingua)
        lingua.setText(currentNotebook.lingua)

        var studente: TextView = v.findViewById <TextView>(R.id.contenutoStudente)
        studente.setText(currentNotebook.studente)


        var data: TextView = v.findViewById <TextView>(R.id.contenutoData)
        data.setText(currentNotebook.dataCaricamento)

        when (currentNotebook.materia){
            1 ->{if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(an1_ing)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(an1_ing_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }
            3 ->{if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(geom_ing)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(geom_ing_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }

            4 ->{if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(micro_eco)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(micro_eco_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }

            7 ->{if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(dciv_giur)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
                else
                Glide.with(context)
                    .load(dciv_giur_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }
            11 ->{if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(an1_ing)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(an1_ing_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }
            15 ->{if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(dirpriv_eco)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(dirpriv_eco_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }
            17 -> {if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(deccl_giur)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(deccl_giur_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }
        }

        holder.image?.setOnClickListener {
            val intent= Intent(context, NotebookViewActivity::class.java)
            intent.putExtra("Nome",currentNotebook.titolo)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return notebooksList.size
    }


}



