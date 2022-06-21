package com.example.coway.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coway.R
import com.example.coway.data.PaymentMethod
import com.example.coway.databinding.ItemViewPaymentMethodBinding
import com.example.coway.utils.gone
import com.example.coway.utils.visible

class PaymentMethodAdapter(var list: MutableList<PaymentMethod>) :
    RecyclerView.Adapter<PaymentMethodAdapter.MyViewHolder>(){

    var listener: AdapterClickListener<PaymentMethod>? = null
    var selectedPayment: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodAdapter.MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_payment_method, parent, false
        ))
    }

    override fun onBindViewHolder(holder: PaymentMethodAdapter.MyViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<PaymentMethod>?) {
        this.list.clear()
        if (list != null) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder(var binding: ItemViewPaymentMethodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(data: PaymentMethod) {
            binding.data = data
            if (selectedPayment == data.paymentMethodName) {
                binding.checklist.visible()
            } else {
                binding.checklist.gone()
            }
            itemView.setOnClickListener {
                listener?.onItemClick(data)
            }
        }

    }

}