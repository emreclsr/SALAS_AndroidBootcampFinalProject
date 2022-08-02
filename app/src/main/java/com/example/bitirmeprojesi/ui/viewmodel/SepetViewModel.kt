package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetViewModel @Inject constructor(var yemekrepo: YemeklerDaoRepository): ViewModel() {
}