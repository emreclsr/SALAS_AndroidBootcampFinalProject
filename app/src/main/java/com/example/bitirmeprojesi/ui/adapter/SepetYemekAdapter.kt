package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.databinding.SepetCardTasarimBinding
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel

// 1. Parametre tanımla
class SepetYemekAdapter(var mContext:Context,
                        var sepetYemekListesi:List<SepetYemekler>,
                        var viewModel:SepetViewModel) : RecyclerView.Adapter<SepetYemekAdapter.SepetCardTasarimTutucu>(){

    inner class SepetCardTasarimTutucu(tasarim: SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:SepetCardTasarimBinding
        init  {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = SepetCardTasarimBinding.inflate(layoutInflater, parent, false)
        return SepetCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepetYemek = sepetYemekListesi.get(position)
        val t = holder.tasarim
        t.sepetYemekNesnesi = sepetYemek
    }

    override fun getItemCount(): Int {
        return sepetYemekListesi.size
    }
}