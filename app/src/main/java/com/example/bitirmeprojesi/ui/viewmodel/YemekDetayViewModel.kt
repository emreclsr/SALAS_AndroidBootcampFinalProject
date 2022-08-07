package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YemekDetayViewModel @Inject constructor(var yemekrepo: YemeklerDaoRepository): ViewModel(){
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepetYemekleriYukle("emreclsr")
        sepetYemeklerListesi = yemekrepo.sepetYemekleriGetir()
    }

    fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        yemekrepo.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    fun sepetYemekleriYukle(kullanici_adi:String){
        yemekrepo.tumSepetYemekleriAl(kullanici_adi)
    }

    fun sepetYemekSil(sepet_yemek_id:Int, kullanici_adi: String){
        yemekrepo.sepetYemekSil(sepet_yemek_id, kullanici_adi)
    }
}