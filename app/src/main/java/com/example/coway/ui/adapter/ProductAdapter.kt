package com.example.coway.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coway.R
import com.example.coway.data.Product
import com.example.coway.databinding.ItemViewProductBinding
import com.example.coway.utils.setImage

class ProductAdapter(var list: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>(){

    var listener: AdapterClickListener<Product>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_product, parent, false
        ))
    }

    override fun onBindViewHolder(holder: ProductAdapter.MyViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<Product>?) {
        this.list.clear()
        if (list != null) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder(var binding: ItemViewProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(data: Product) {
            binding.data = data
            binding.tvBasePrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.setOnClickListener {
                listener?.onItemClick(data)
            }
            binding.image.setImage(data.image)
        }

    }

}