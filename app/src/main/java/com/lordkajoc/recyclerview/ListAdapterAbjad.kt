package com.lordkajoc.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lordkajoc.recyclerview.databinding.CardviewListItemBinding

class ListAdapterAbjad(private val listAbjad: ArrayList<KumpulanAbjad>) :
    RecyclerView.Adapter<ListAdapterAbjad.ViewHolder>() {
    //Class ViewHolder
    class ViewHolder(var binding: CardviewListItemBinding) : RecyclerView.ViewHolder(binding.root)

    //Membuat holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardviewListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    //Melakukan penentuan data yang akan ditampilkan pada setiap item/baris
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textviewabjad = listAbjad[position].abjad
        holder.binding.tvabjad.text = textviewabjad
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                //Mengirim data menggunakan Bundle dengan data yang dibawa textabjad pada list
                val bundle = Bundle()
                bundle.putString("ABJAD", textviewabjad)
                //data dibawa ke FragmentKumpulanKata
                val kumpulanKataFrag = FragmentKumpulanKata()
                kumpulanKataFrag.arguments = bundle
                holder.itemView.findNavController().navigate(R.id.action_fragmentListLinearLayout_to_fragmentKumpulanKata,bundle)
            }
        })
    }
    override fun getItemCount(): Int {
        return listAbjad.size
    }
}