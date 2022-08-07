package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.data.entity.Banner
import com.example.bitirmeprojesi.databinding.BannerCardTasarimBinding
import java.math.MathContext

class BannerAdapter(var mContext: Context, var bannerListesi:List<Banner>) : RecyclerView.Adapter<BannerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim:BannerCardTasarimBinding):RecyclerView.ViewHolder(tasarim.root){
        var tasarim: BannerCardTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = BannerCardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val banner = bannerListesi.get(position)
        val t = holder.tasarim

        t.imageView.setImageResource(
            mContext.resources.getIdentifier(banner.resimAdi, "drawable", mContext.packageName)
        )
    }

    override fun getItemCount(): Int {
        return bannerListesi.size
    }
}