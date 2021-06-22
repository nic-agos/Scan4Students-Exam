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
import it.skotlinyard.scan4students.utils.Session
import it.skotlinyard.scan4students.view.NotebookViewActivity

class NotebookAdapter(private var context: Context, private var notebooksList: MutableList<Quaderni>) :
    RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder>() {

    class NotebookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView?=null
        var lingua: TextView?=null
        var data: TextView?=null
        var studente: TextView?=null
        init {
            image=itemView.findViewById(R.id.notebookImage)
            lingua=itemView.findViewById(R.id.contenutoLingua)
            data=itemView.findViewById(R.id.contenutoData)
            studente=itemView.findViewById(R.id.contenutoStudente)
        }
        var currentNotebook: Quaderni?= Quaderni("",0,0,"","","","")
            set(value) {
                field=value
                lingua?.setText(field?.lingua)
                data?.setText(field?.dataCaricamento)
                studente?.setText(field?.titolo + R.string.by+ field?.studente)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_recycler_notebook,parent,false)
        return NotebookViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotebookViewHolder, position: Int) {

        holder.currentNotebook=notebooksList[position]
        var currentNotebook=notebooksList[position]

        when (currentNotebook.materia){
            1,11 ->{if (currentNotebook.visibilita=="PUBLIC")
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
            3,10 ->{if (currentNotebook.visibilita=="PUBLIC")
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

            4,13 ->{if (currentNotebook.visibilita=="PUBLIC")
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

            7,16 ->{if (currentNotebook.visibilita=="PUBLIC")
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

            6,15 ->{if (currentNotebook.visibilita=="PUBLIC")
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
            8,17 -> {if (currentNotebook.visibilita=="PUBLIC")
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
            2,12 -> {if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(fis1_ing)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(fis1_ing_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }
            5,14 -> {if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(ecoaz_eco)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(ecoaz_eco_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }
            9,18 -> {if (currentNotebook.visibilita=="PUBLIC")
                Glide.with(context)
                    .load(dpub_giur)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            else
                Glide.with(context)
                    .load(dpub_giur_priv)
                    .apply(RequestOptions().centerCrop())
                    .into(holder.image!!)
            }

        }

        holder.image?.setOnClickListener {
            val intent= Intent(context, NotebookViewActivity::class.java)
            intent.putExtra("Nome",currentNotebook.titolo)
            if (Session.getCurrUsername()==currentNotebook.studente)
                intent.putExtra("add_btn",true)
            else
                intent.putExtra("add_btn",false)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return notebooksList.size
    }


}



