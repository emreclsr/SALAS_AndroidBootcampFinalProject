package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var yemekrepo: YemeklerDaoRepository): ViewModel() {
    var sepetYemekListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepetYemekleriYukle(kullanici_adi = "emreclsr")
        sepetYemekListesi = yemekrepo.sepetYemeklerGetir()
        Log.e("testyazisi sepetviewmodel", sepetYemekListesi.value.toString())
    }

    fun sepetYemekleriYukle(kullanici_adi:String){
        yemekrepo.sepetYemeklerAl(kullanici_adi)
    }
}