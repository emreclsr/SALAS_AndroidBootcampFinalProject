package com.example.bitirmeprojesi.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.entity.YemeklerCevap
import com.example.bitirmeprojesi.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository(var yemekdao: YemeklerDao) {
    var yemeklerListesi:MutableLiveData<List<Yemekler>>

    init{
        yemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
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
}