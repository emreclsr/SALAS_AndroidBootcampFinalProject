package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentSepetBinding
import com.example.bitirmeprojesi.ui.adapter.SepetYemekAdapter
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var tasarim:FragmentSepetBinding
    private lateinit var viewModel:SepetViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        tasarim.sepetFragment = this

        tasarim.sepetToolbarBaslik = "Sepetim"

        viewModel.sepetYemekListesi.observe(viewLifecycleOwner){
            val adapterSepet = SepetYemekAdapter(requireContext(), it, viewModel)
            tasarim.sepetYemekAdapter = adapterSepet
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel:SepetViewModel by viewModels()
        viewModel = tempViewModel
    }
}