package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.bitirmeprojesi.DataBinderMapperImpl
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentHesapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HesapFragment : Fragment() {
    private lateinit var tasarim:FragmentHesapBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_hesap, container, false)
        tasarim.hesapFragment = this
        tasarim.hesapToolbarBaslik = "Hesabım"

        return tasarim.root
    }
}