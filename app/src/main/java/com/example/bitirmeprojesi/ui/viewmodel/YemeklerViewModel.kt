package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YemeklerViewModel @Inject constructor (var yemekrepo:YemeklerDaoRepository):ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init{
        yemekleriYukle()
        yemeklerListesi = yemekrepo.yemekleriGetir()
    }

    fun yemekleriYukle(){
        yemekrepo.tumYemekleriAl()
    }
}