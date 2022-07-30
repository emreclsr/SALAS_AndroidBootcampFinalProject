package com.example.bitirmeprojesi.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("resimGetir")
fun resimGoster(view: ImageView, resimAdi: String) {
    val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
        Picasso.get()
            .load(url)
            .into(view)
}