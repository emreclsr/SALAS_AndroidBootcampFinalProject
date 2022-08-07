package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentSepetBinding
import com.example.bitirmeprojesi.ui.adapter.SepetYemeklerAdapter
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

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){
            val adapterSepet = SepetYemeklerAdapter(requireContext(), it, viewModel)
            tasarim.sepetYemeklerAdapter = adapterSepet

            if (viewModel.sepetYemeklerListesi.value?.size == 0){
                tasarim.rvVisibility = false
            } else {
                tasarim.rvVisibility = true
            }

            var toplamTutar=0
            for (i in viewModel.sepetYemeklerListesi.value!!) {
                toplamTutar = toplamTutar + i.yemek_fiyat
            }
            tasarim.toplamTutar = toplamTutar
        }



        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetViewModel by viewModels()
        viewModel = tempViewModel
    }
}