package com.lordkajoc.recyclerview

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.lordkajoc.recyclerview.databinding.CardviewListItemBinding

class ListAdapterHuruf(private val listHuruf: ArrayList<KumpulanKata>) :
    RecyclerView.Adapter<ListAdapterHuruf.ViewHolder>() {
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
        val textviewKata = listHuruf[position].katakata
        holder.binding.tvabjad.text = textviewKata
        //mengaktifkan item pada list agar dapat di klik
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val transaction = p0?.context as AppCompatActivity
                //intent activity membawa data textpada item untuk disearch pada google
                val pindahweb = Intent(Intent.ACTION_VIEW)
                pindahweb.data = Uri.parse("https://www.google.com/search?q=$textviewKata")
                transaction.startActivity(pindahweb)
            }
        })
    }

    override fun getItemCount(): Int {
        return listHuruf.size
    }
}