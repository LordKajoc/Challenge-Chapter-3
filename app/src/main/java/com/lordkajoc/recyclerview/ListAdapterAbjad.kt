package com.lordkajoc.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lordkajoc.recyclerview.databinding.CardviewListItemBinding

class ListAdapterAbjad(
    private val kumpulanAbjad: ArrayList<KumpulanAbjad>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapterAbjad.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kumpulanAbjad[position])
    }

    override fun getItemCount(): Int {
        return kumpulanAbjad.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val tvAbjad: TextView = itemView.findViewById(R.id.tv_abjad)

        fun bind(abjad: KumpulanAbjad) {
            tvAbjad.text = abjad.abjad
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(kumpulanAbjad[position].abjad)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(abjad: String)
        fun onIconClick()
    }
}