package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YemekDetayViewModel @Inject constructor(var yemekrepo: YemeklerDaoRepository): ViewModel(){
    fun sepeteEkle(yemek_id:Int, yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:String){
    }
}