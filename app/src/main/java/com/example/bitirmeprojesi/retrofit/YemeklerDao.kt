package com.example.bitirmeprojesi.retrofit

import com.example.bitirmeprojesi.data.entity.CRUDCevap
import com.example.bitirmeprojesi.data.entity.SepetYemeklerCevap
import com.example.bitirmeprojesi.data.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {
    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler() : Call<YemeklerCevap>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteYemekEkle(@Field("yemek_adi") yemek_adi:String,
                        @Field("yemek_resim_adi") yemek_resim_adi:String,
                        @Field("yemek_fiyat") yemek_fiyat:Int,
                        @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                        @Field("kullanici_adi") kullanici_adi:String) : Call<CRUDCevap>



    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepetGoster(@Field("kullanici_adi") kullanici_adi: String) : Call<SepetYemeklerCevap>



    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepetYemekSil(@Field("sepet_yemek_id") sepet_yemek_id: Int,
                      @Field("kullanici_adi") kullanici_adi: String) : Call<CRUDCevap>

}