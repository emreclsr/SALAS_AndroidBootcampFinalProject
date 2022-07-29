package com.example.bitirmeprojesi.retrofit

import com.example.bitirmeprojesi.data.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.GET

interface YemeklerDao {
    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler() : Call<YemeklerCevap>
}