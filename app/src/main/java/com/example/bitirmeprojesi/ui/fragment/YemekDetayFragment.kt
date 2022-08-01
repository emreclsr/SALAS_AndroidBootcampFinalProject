package com.example.bitirmeprojesi.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.bitirmeprojesi.databinding.FragmentYemekDetayBinding
import com.example.bitirmeprojesi.ui.viewmodel.YemekDetayViewModel


class YemekDetayFragment : Fragment() {
    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, com.example.bitirmeprojesi.R.layout.fragment_yemek_detay, container, false)
        tasarim.yemekDetayFragment = this

        tasarim.yemekDetayToolbarBaslik = "Yemek Detayı"

        val bundle:YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        tasarim.yemekDetayNesnesi = gelenYemek

        tasarim.adet = "1"
        tasarim.fiyat = "${gelenYemek.yemek_fiyat} ₺"

        return tasarim.root
    }


    fun buttonArtır(){
        tasarim.adet = (tasarim.adet!!.toInt() + 1).toString()

        tasarim.fiyat = "${(tasarim.adet!!.toInt()*tasarim.yemekDetayNesnesi!!.yemek_fiyat.toInt())} ₺"
    }
    fun buttonAzalt(){
        if (tasarim.adet!!.toInt() > 1) {
            tasarim.adet = (tasarim.adet!!.toInt() - 1).toString()

            tasarim.fiyat = "${(tasarim.adet!!.toInt() * tasarim.yemekDetayNesnesi!!.yemek_fiyat.toInt())} ₺"
        }
    }
}