package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentSepetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var tasarim:FragmentSepetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)

        //TODO: Navigation geçiş için anaekrana fab buton ekle SepetYemekAdapter, fragment_sepet ve sepet_card_tasarim'da düzenlemeler yapıldı, api bağlantılarını tamamla.


        return tasarim.root
    }
}