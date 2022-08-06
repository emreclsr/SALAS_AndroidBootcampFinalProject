package com.example.bitirmeprojesi.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bitirmeprojesi.data.entity.*
import com.example.bitirmeprojesi.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository(var yemekdao:YemeklerDao) {
    var yemeklerListesi:MutableLiveData<List<Yemekler>>
    var sepetYemekListesi:MutableLiveData<List<SepetYemekler>>

    init{
        yemeklerListesi = MutableLiveData()
        sepetYemekListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
    }

    fun sepetYemeklerGetir() : MutableLiveData<List<SepetYemekler>>{
        return sepetYemekListesi
    }

    fun tumYemekleriAl(){
        yemekdao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }

            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun sepeteYemekEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi: String){
        yemekdao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(object:Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message
                Log.e("testyazisi yemeklerdaorepository sepetyemekekle", "$basari - $mesaj - $kullanici_adi - $yemek_adi")
            }
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun sepetYemeklerAl(kullanici_adi:String){
        yemekdao.sepetYemeklerAl(kullanici_adi).enqueue(object:Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>?, response: Response<SepetYemeklerCevap>) {
                val sepetListe = response.body().sepet_yemekler
                val basari = response.body().success
                sepetYemekListesi.value = sepetListe
                Log.e("testyazisi yemeklerdaorepository sepetyemekleral", "$basari - $sepetListe")
            }
            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {}
        })
    }
}