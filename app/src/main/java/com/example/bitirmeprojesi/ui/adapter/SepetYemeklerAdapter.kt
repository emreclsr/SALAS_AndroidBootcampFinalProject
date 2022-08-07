package com.example.bitirmeprojesi.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.databinding.CardTasarimSepetBinding
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit


class SepetYemeklerAdapter(var mContext:Context,
                           var sepetYemeklerListesi:List<SepetYemekler>,
                           var viewModel:SepetViewModel) : RecyclerView.Adapter<SepetYemeklerAdapter.CardTasarimTutucuSepet>(){

    inner class CardTasarimTutucuSepet(tasarim:CardTasarimSepetBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardTasarimSepetBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucuSepet {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardTasarimSepetBinding.inflate(layoutInflater, parent,false)
        return CardTasarimTutucuSepet(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucuSepet, position: Int) {
        val sepetYemek = sepetYemeklerListesi.get(position)
        val t = holder.tasarim

        t.textViewYemekAdiSepet.text = "${sepetYemek.yemek_adi}"
        t.textViewYemekFiyatSepet.text = "${sepetYemek.yemek_fiyat} ₺"
        t.textViewYemekAdetSepet.text = "${sepetYemek.yemek_siparis_adet}"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Picasso.get().load(url).into(t.imageViewYemekResimSepet)

        t.imageViewYemekSil.setOnClickListener {
            Snackbar.make(it,"${sepetYemek.yemek_adi} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET") { viewModel.sepetYemekSil(sepetYemek.sepet_yemek_id, kullanici_adi = "emreclsr")
                    viewModel.sepetYemekleriYukle(kullanici_adi = "emreclsr")}
                .setActionTextColor(Color.RED)
                .setTextColor(Color.BLACK)
                .setBackgroundTint(Color.WHITE)
                .show()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return sepetYemeklerListesi.size
    }
}