package com.example.mycryptoinfoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso


class CoinDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel

    private lateinit var tvFromSymbol: TextView
    private lateinit var tvToSymbol: TextView
    private lateinit var tvPriceLabel: TextView
    private lateinit var tvMinPriceLabel: TextView
    private lateinit var tvMaxPriceLabel: TextView
    private lateinit var tvLastMarketLabel: TextView
    private lateinit var tvLastUpdateLabel: TextView

    private lateinit var ivLogoCoin: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        initViews()

        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.tosymbol
                tvPriceLabel.text = it.price.toString()
                tvMinPriceLabel.text = it.lowday.toString()
                tvMaxPriceLabel.text = it.highday.toString()
                tvLastMarketLabel.text = it.lastmarket
                tvLastUpdateLabel.text = it.getFormattedTime()

                Picasso.get().load(it.getFullImageURL()).into(ivLogoCoin)

            })
        }


    }

    fun initViews(){
        tvFromSymbol =  findViewById(R.id.tvFromSymbol)
        tvToSymbol = findViewById(R.id.tvToSymbol)
        tvPriceLabel = findViewById(R.id.tvPriceLabel)
        tvMinPriceLabel = findViewById(R.id.tvMinPriceLabel)
        tvMaxPriceLabel = findViewById(R.id.tvMaxPriceLabel)
        tvLastMarketLabel = findViewById(R.id.tvLastMarketLabel)
        tvLastUpdateLabel = findViewById(R.id.tvLastUpdateLabel)
        ivLogoCoin = findViewById(R.id.ivLogoCoin)

    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}