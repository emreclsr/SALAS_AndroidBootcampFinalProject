package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Banner
import com.example.bitirmeprojesi.databinding.FragmentYemeklerBinding
import com.example.bitirmeprojesi.ui.adapter.BannerAdapter
import com.example.bitirmeprojesi.ui.adapter.YemeklerAdapter
import com.example.bitirmeprojesi.ui.viewmodel.YemeklerViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemeklerFragment : Fragment() {
    private lateinit var tasarim:FragmentYemeklerBinding
    private lateinit var viewModel:YemeklerViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemekler, container, false)
        tasarim.yemeklerFragment = this

        // Hide navigation bar
        activity!!.window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)


        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter = YemeklerAdapter(requireContext(), it, viewModel)
            tasarim.yemeklerAdapter = adapter
        }

        val bannerListesi = ArrayList<Banner>()
        bannerListesi.add(Banner("banner1"))
        bannerListesi.add(Banner("banner2"))
        bannerListesi.add(Banner("banner3"))
        bannerListesi.add(Banner("banner4"))
        bannerListesi.add(Banner("banner5"))
        bannerListesi.add(Banner("banner6"))

        val adapterBanner = BannerAdapter(requireContext(), bannerListesi)
        tasarim.bannerAdapter = adapterBanner


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel:YemeklerViewModel by viewModels()
        viewModel = tempViewModel
    }
}