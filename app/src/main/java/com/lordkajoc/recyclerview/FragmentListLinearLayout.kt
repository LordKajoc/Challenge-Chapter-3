package com.lordkajoc.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lordkajoc.recyclerview.databinding.FragmentListLinearLayoutBinding

class FragmentListLinearLayout : Fragment(), ListAdapterAbjad.OnItemClickListener {

    private lateinit var binding: FragmentListLinearLayoutBinding
    private lateinit var adapter: ListAdapterAbjad

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListLinearLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val abjadList = getlistabjad()
        adapter = ListAdapterAbjad(abjadList, this)
        binding.recyclerViewAbjad.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewAbjad.adapter = adapter
        val imageclick = binding.ivIcgrid
        imageclick.setOnClickListener {
            onIconClick()
        }
    }

    override fun onIconClick() {
        findNavController().navigate(R.id.action_listlinearlayout_to_fragmentListGridLayout)
    }

    override fun onItemClick(abjad: String) {
        val action =
            FragmentListLinearLayoutDirections.actionListlinearlayoutToListKataFragment(abjad)
        findNavController().navigate(action)
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