package com.lafimsize.cryptocurrency.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lafimsize.cryptocurrency.databinding.RecyclerRowBinding
import com.lafimsize.cryptocurrency.model.CryptoModel
import com.lafimsize.cryptocurrency.view.MainActivity

class RecyclerAdapter(private val cryptoList:ArrayList<CryptoModel>, private val listener:Listener): RecyclerView.Adapter<RecyclerAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    private val colors= arrayOf("#33D2FF","#F6FF33","#FF338C","#9733FF")



    class RowHolder(val binding: RecyclerRowBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(cryptoModel: CryptoModel, colors:Array<String>,position: Int,listener:Listener){
            binding.linerl.setOnClickListener{
                listener.onItemClick(cryptoModel)
            }
            binding.linerl.setBackgroundColor(Color.parseColor(colors[position%4]))
            binding.textName.text=cryptoModel.currency
            binding.textPrice.text=cryptoModel.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding= RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

}