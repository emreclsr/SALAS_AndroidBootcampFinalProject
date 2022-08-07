package com.example.bitirmeprojesi.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bitirmeprojesi.data.entity.*
import com.example.bitirmeprojesi.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.EOFException

class YemeklerDaoRepository(var yemekdao:YemeklerDao) {
    var yemeklerListesi:MutableLiveData<List<Yemekler>>
    var sepetYemeklerListesi:MutableLiveData<List<SepetYemekler>>

    init{
        yemeklerListesi = MutableLiveData()
        sepetYemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
    }

    fun sepetYemekleriGetir() : MutableLiveData<List<SepetYemekler>>{
        return sepetYemeklerListesi
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
                tumSepetYemekleriAl(kullanici_adi)
            }
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun tumSepetYemekleriAl(kullanici_adi: String) {
        yemekdao.sepetGoster(kullanici_adi).enqueue(object: Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>?, response: Response<SepetYemeklerCevap>) {
                val sepetListe = response.body().sepet_yemekler
                sepetYemeklerListesi.value = sepetListe
            }
            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {
                sepetYemeklerListesi.value = listOf()
            }
        })
    }

    fun sepetYemekSil(sepet_yemek_id: Int, kullanici_adi: String) {
        yemekdao.sepetYemekSil(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<CRUDCevap> {
                override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                    val basari = response.body().success
                    val mesaj = response.body().message
                    tumSepetYemekleriAl(kullanici_adi)
                }
                override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
            })
    }
}