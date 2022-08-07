package com.example.bitirmeprojesi.ui.fragment

import android.R
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.bitirmeprojesi.databinding.FragmentYemekDetayBinding
import com.example.bitirmeprojesi.ui.viewmodel.YemekDetayViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, com.example.bitirmeprojesi.R.layout.fragment_yemek_detay, container, false)
        tasarim.yemekDetayFragment = this

        // Hide navigation bar
        activity!!.window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)


        val bundle:YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        tasarim.yemekDetayNesnesi = gelenYemek

        var sepetYemekler = viewModel.sepetYemeklerListesi.value?.toList()

        if (sepetYemekler.isNullOrEmpty()) {
            tasarim.adet = 1
            tasarim.fiyat = gelenYemek.yemek_fiyat.toInt()
        } else {
            for (i in sepetYemekler){
                if (i.yemek_adi == gelenYemek.yemek_adi){
                    tasarim.adet = i.yemek_siparis_adet
                    tasarim.fiyat = tasarim.adet!!*tasarim.yemekDetayNesnesi!!.yemek_fiyat.toInt()
                    break
                } else{
                    tasarim.adet = 1
                    tasarim.fiyat = tasarim.adet!!*tasarim.yemekDetayNesnesi!!.yemek_fiyat.toInt()
                }
            }
        }
        return tasarim.root
    }

    fun buttonArtır(){
        tasarim.adet = tasarim.adet!! + 1

        tasarim.fiyat = tasarim.adet!!*tasarim.yemekDetayNesnesi!!.yemek_fiyat.toInt()
    }
    fun buttonAzalt(){
        if (tasarim.adet!! > 1) {
            tasarim.adet = tasarim.adet!! - 1

            tasarim.fiyat = tasarim.adet!!.toInt() * tasarim.yemekDetayNesnesi!!.yemek_fiyat.toInt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:YemekDetayViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        var sepetYemekler = viewModel.sepetYemeklerListesi.value?.toList()
        TimeUnit.SECONDS.sleep(1)
        if (sepetYemekler.isNullOrEmpty()){
            viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }else{
            for (i in sepetYemekler){
                if (i.yemek_adi == yemek_adi){
                    viewModel.sepetYemekSil(i.sepet_yemek_id, "emreclsr")
                }
            }
            viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
        activity?.onBackPressed()
    }

    fun geriDon(){
        activity?.onBackPressed()
    }
}