package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.databinding.YemekCardTasarimBinding
import com.example.bitirmeprojesi.ui.fragment.YemeklerFragmentDirections
import com.example.bitirmeprojesi.ui.viewmodel.YemeklerViewModel
import com.example.bitirmeprojesi.utils.gecisYap
import com.squareup.picasso.Picasso

// 1. Parametre tanımla
class YemeklerAdapter(var mContext: Context,
                      var yemeklerListesi:List<Yemekler>,
                      var viewModel:YemeklerViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.YemekCardTasatimTutucu>() { // 3. inner classı adaptera bağla
    // 2. Card tasarımı sınıfı oluştur.
    inner class YemekCardTasatimTutucu(tasarim:YemekCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {
        var tasarim:YemekCardTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekCardTasatimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = YemekCardTasarimBinding.inflate(layoutInflater, parent, false)
        return YemekCardTasatimTutucu(tasarim) // 5. Card tasarim için view binding kurulumu
    }

    override fun onBindViewHolder(holder: YemekCardTasatimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        t.yemekNesnesi = yemek

        t.yemekCard.setOnClickListener {
            val gecis = YemeklerFragmentDirections.yemekDetayGecis(yemek=yemek)
            Navigation.gecisYap(it, gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size // 4. Satır sayısını belirle
    }
}