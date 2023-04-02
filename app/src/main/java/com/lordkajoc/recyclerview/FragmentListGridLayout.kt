package com.lordkajoc.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lordkajoc.recyclerview.databinding.FragmentListGridLayoutBinding

class FragmentListGridLayout : Fragment(), ListAdapterAbjad.OnItemClickListener {

    private lateinit var binding: FragmentListGridLayoutBinding
    private lateinit var adapter: ListAdapterAbjad

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListGridLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val abjadList = getlistabjad()
        adapter = ListAdapterAbjad(abjadList, this)
        binding.recyclerViewAbjad.layoutManager = GridLayoutManager(context,2)
        binding.recyclerViewAbjad.adapter = adapter
        val imageclick = binding.ivIclist
        imageclick.setOnClickListener {
            onIconClick()
        }
    }

    override fun onItemClick(abjad: String) {
        val action = FragmentListGridLayoutDirections.actionFragmentListGridLayoutToListKataFragment(abjad)
        findNavController().navigate(action)
    }

    override fun onIconClick() {
        findNavController().navigate(R.id.action_fragmentListGridLayout_to_listlinearlayout)
    }

    private fun getlistabjad(): ArrayList<KumpulanAbjad> {
        val dataNama = resources.getStringArray(R.array.data_abjad)
        val kumpulanAbjad = ArrayList<KumpulanAbjad>()
        for (i in dataNama.indices) {
            val abjad = KumpulanAbjad(dataNama[i])
            kumpulanAbjad.add(abjad)
        }
        return kumpulanAbjad
    }
}
