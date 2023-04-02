package com.lordkajoc.recyclerview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lordkajoc.recyclerview.databinding.FragmentKumpulanKataBinding

class FragmentKumpulanKata : Fragment(), ListAdapterHuruf.OnItemClickListener {

    private lateinit var binding: FragmentKumpulanKataBinding
    private lateinit var adapter: ListAdapterHuruf
    private lateinit var abjad: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKumpulanKataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ambil argumen abjad dari navigasi
        abjad = FragmentKumpulanKataArgs.fromBundle(requireArguments()).abjad
        binding.textView.text = "Word That Start With $abjad"
        // buat daftar kata berdasarkan abjad
        val kataList = generateKataList(abjad)

        // set adapter untuk RecyclerView
        adapter = ListAdapterHuruf(kataList, this)
        binding.recyclerViewKataa.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewKataa.adapter = adapter
        val imageclick = binding.ivIcback
        imageclick.setOnClickListener {
            onIconClick()
        }
    }

    private fun generateKataList(abjad: String): ArrayList<KumpulanKata> {
        val list = ArrayList<KumpulanKata>()
        when (abjad) {
            abjad -> list.addAll(getlistKata(abjad))
            // tambahkan logika untuk abjad lainnya di sini
        }
        return list
    }

    private fun getlistKata(abjad: String): ArrayList<KumpulanKata> {
        //mengambil resource pada string.xml
        val dataKata = resources.getStringArray(R.array.Huruf)

        //resource dimasukan sehingga menjadi bentuk ArrayList untuk mengisi Value pada Class KumpulanKata
        val kumpulanKata = ArrayList<KumpulanKata>()
        for (i in dataKata.indices) {
            //membuat ketentuan untuk resource huruf awal
            val firstletter = dataKata[i].take(1)
            //jika huruf awal resource sesuai dengan abjad data yang diterima
            if (firstletter == abjad) {
                //maka resource akan ditampilkan/ ditampung pada ArrayList KumpulanKata
                val kata = KumpulanKata(dataKata[i])
                kumpulanKata.add(kata)
            }
        }
        return kumpulanKata
    }

    override fun onItemClick(kata: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=$kata"))
        startActivity(intent)
    }

    override fun onIconClick() {
        findNavController().navigate(R.id.action_listKataFragment_to_listlinearlayout)
    }
}
