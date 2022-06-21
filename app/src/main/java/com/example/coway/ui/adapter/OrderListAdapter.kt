package com.example.coway.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coway.R
import com.example.coway.data.Order
import com.example.coway.data.PaymentMethod
import com.example.coway.databinding.ItemViewOrderBinding
import com.example.coway.databinding.ItemViewPaymentMethodBinding
import com.example.coway.utils.gone
import com.example.coway.utils.visible

class OrderListAdapter(var list: MutableList<Order>) :
    RecyclerView.Adapter<OrderListAdapter.MyViewHolder>(){

    var listener: AdapterClickListener<Order>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListAdapter.MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_order, parent, false
        ))
    }

    override fun onBindViewHolder(holder: OrderListAdapter.MyViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<Order>?) {
        this.list.clear()
        if (list != null) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder(var binding: ItemViewOrderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(data: Order) {
            binding.data = data

            when(data.status) {
                "Menunggu Pembayaran" -> {
                    binding.tvStatusOrder.setTextColor(Color.parseColor("#3990BF"))
                }
                "Pesanan Dibatalkan" -> {
                    binding.tvStatusOrder.setTextColor(Color.parseColor("#EB5757"))
                }
                "Pesanan Diproses" -> {
                    binding.tvStatusOrder.setTextColor(Color.parseColor("#3990BF"))
                }
            }
            itemView.setOnClickListener {
                listener?.onItemClick(data)
            }
        }

    }

}