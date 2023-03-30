package com.lordkajoc.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lordkajoc.recyclerview.databinding.FragmentKumpulanKataBinding

class FragmentKumpulanKata : Fragment() {
    val listkata = ArrayList<KumpulanKata>()
    lateinit var binding: FragmentKumpulanKataBinding
    lateinit var recyclerviewKata: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKumpulanKataBinding.inflate(layoutInflater, container, false)
        val args = this.arguments
        val dikirimdata = args?.get("ABJAD")

        //menampilkan Toolbar sesuai dengan Data yang Dikirim (klik item)
        binding.tbStartword.textView.text = "Word That Start With $dikirimdata"
        recyclerviewKata = binding.recyclerviewwordlinear
        recyclerviewKata.setHasFixedSize(true)
        //memanggil Method RecyclerView Tampilkan Item/kata awalan dari abjad yang dipilih
        showRecyclerListWordLinear()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //integrasi image icon back untuk kembali ke List sebelumnya
        val imageback = binding.tbStartword.ivIcback
        val fragmentback = FragmentListLinearLayout()
        imageback.setOnClickListener {
            setCurrentFragment(fragmentback)
        }
    }

    //Method untuk mendapatkan data untuk ArrayList pada data Class KumpulanKata
    private fun getlistKata(): ArrayList<KumpulanKata> {
        //menerima data dari Fragment sebelumnya berupa String dari list abjad yang dipilih
        val args = this.arguments
        val dikirimdata = args?.get("ABJAD")

        //mengambil resource pada string.xml
        val dataKata = resources.getStringArray(R.array.Huruf)

        //resource dimasukan sehingga menjadi bentuk ArrayList untuk mengisi Value pada Class KumpulanKata
        val listKata = ArrayList<KumpulanKata>()
            for (i in dataKata.indices) {
                //membuat ketentuan untuk resource huruf awal
                val firstletter = dataKata[i].take(1)
                //jika huruf awal resource sesuai dengan abjad data yang diterima
                if (firstletter == dikirimdata) {
                    //maka resource akan ditampilkan/ ditampung pada ArrayList KumpulanKata
                    val kata = KumpulanKata(dataKata[i])
                    listKata.add(kata)
                }
            }
        return listKata
    }

    private fun showRecyclerListWordLinear() {
        recyclerviewKata.layoutManager = LinearLayoutManager(context)
        val listHurufAdapter = ListAdapterHuruf(listkata)
        recyclerviewKata.adapter = listHurufAdapter
        listkata.clear()
        listkata.addAll(getlistKata())
    }

    private fun setCurrentFragment(fragment: Fragment): FragmentTransaction =
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fr_container, fragment)
            commit()
        }
}