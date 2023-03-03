package com.example.mycryptoinfoapp.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoinfoapp.R
import com.example.mycryptoinfoapp.pojo.CoinInfo
import com.example.mycryptoinfoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList : List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info,parent,false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        val symbole_template = context.resources.getString(R.string.symbols_template)
        val last_update_template = context.resources.getString(R.string.last_update_template)

        holder.tvCoinName.text = String.format(symbole_template, coin.fromSymbol, coin.tosymbol)
        holder.tvCoinPrice.text = coin.price.toString()
        holder.tvLastUpdate.text = String.format(last_update_template, coin.getFormattedTime())
        Picasso.get().load(coin.getFullImageURL()).into(holder.ivLogoCoin)

        holder.itemView.setOnClickListener{
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
        val tvCoinName: TextView = itemView.findViewById(R.id.tvCoinName)
        val tvCoinPrice: TextView = itemView.findViewById(R.id.tvCoinPrice)
        val tvLastUpdate: TextView = itemView.findViewById(R.id.tvLastUpdate)

    }

    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}