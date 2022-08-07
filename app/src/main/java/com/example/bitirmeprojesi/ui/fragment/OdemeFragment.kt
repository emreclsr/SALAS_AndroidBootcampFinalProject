package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.databinding.FragmentOdemeBinding
import com.example.bitirmeprojesi.ui.viewmodel.OdemeViewModel
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.example.bitirmeprojesi.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OdemeFragment : Fragment() {
    private lateinit var tasarim:FragmentOdemeBinding
    private lateinit var viewModel:OdemeViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_odeme, container, false)
        tasarim.odemeFragment = this

        // Hide navigation bar
        activity!!.window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        val bundle: OdemeFragmentArgs by navArgs()
        val gelenSepet = bundle.sepet
        tasarim.sepetTutar = gelenSepet.sepet_tutar
        tasarim.toplamTutar = gelenSepet.toplam_tutar

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: OdemeViewModel by viewModels()
        viewModel = tempViewModel

    }

    fun odemeYap(view:View){
        var sepet = viewModel.sepetYemeklerListesi
        for (i in sepet.value!!){
            viewModel.sepetYemekSil(i.sepet_yemek_id, "emreclsr")
        }
        Toast.makeText(requireContext(), "Siparişiniz alındı.", Toast.LENGTH_SHORT).show()
        activity?.onBackPressed()
    }
}