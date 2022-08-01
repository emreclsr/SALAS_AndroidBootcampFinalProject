package com.example.bitirmeprojesi.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.gecisYap(v: View, id:Int){
    Navigation.findNavController(v).navigate(id)
}

fun Navigation.gecisYap(v: View, id: NavDirections){
    Navigation.findNavController(v).navigate(id)
}