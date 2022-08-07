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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Odeme
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.databinding.FragmentSepetBinding
import com.example.bitirmeprojesi.ui.adapter.SepetYemeklerAdapter
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.example.bitirmeprojesi.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var tasarim:FragmentSepetBinding
    private lateinit var viewModel:SepetViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        tasarim.sepetFragment = this


        // Hide navigation bar
        activity!!.window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

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

    fun odemeyeGec(view:View, sepet_tutarı:Int){
        var sepet = Odeme(sepet_tutar=sepet_tutarı, toplam_tutar = sepet_tutarı+10)
        val gecis = SepetFragmentDirections.odemeGecis(sepet= sepet)
        Navigation.gecisYap(view, gecis)
        }
}