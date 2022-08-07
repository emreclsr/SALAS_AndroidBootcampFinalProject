package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OdemeViewModel @Inject constructor(var yemekrepo:YemeklerDaoRepository): ViewModel(){
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepetYemekleriYukle(kullanici_adi = "emreclsr")
        sepetYemeklerListesi = yemekrepo.sepetYemekleriGetir()
    }

    fun sepetYemekleriYukle(kullanici_adi:String){
        yemekrepo.tumSepetYemekleriAl(kullanici_adi)
    }

    fun sepetYemekSil(sepet_yemek_id:Int, kullanici_adi: String){
        yemekrepo.sepetYemekSil(sepet_yemek_id, kullanici_adi)
    }

    fun yemekleriYukle(){
        yemekrepo.tumYemekleriAl()
    }
}