package com.example.mycryptoinfoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    val TAG = "Main"
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_pricelist)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
//        viewModel.priceList.observe(this, Observer {
//            Log.d(TAG, "Data loading success: + ${it.toString()}")
//        })

        viewModel.getDetailInfo("BTC").observe(this, Observer {
            Log.d(TAG, "Data loading success: + ${it.toString()}")
        })
    }
}