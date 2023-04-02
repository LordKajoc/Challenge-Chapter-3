package com.lordkajoc.recyclerview

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.lordkajoc.recyclerview.databinding.CardviewListItemBinding

class ListAdapterHuruf(
    private val kumpulanKata: ArrayList<KumpulanKata>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<ListAdapterHuruf.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kumpulanKata[position])
    }

    override fun getItemCount(): Int {
        return kumpulanKata.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val tvKata: TextView = itemView.findViewById(R.id.tv_abjad)

        fun bind(kata: KumpulanKata) {
            tvKata.text = kata.katakata
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(kumpulanKata[position].katakata)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(kata: String)
        fun onIconClick()
    }
}
