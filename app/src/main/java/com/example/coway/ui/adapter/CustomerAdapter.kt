package com.example.coway.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coway.R
import com.example.coway.data.User
import com.example.coway.databinding.ItemViewCustomerBinding

class CustomerAdapter(var list: MutableList<User>) :
    RecyclerView.Adapter<CustomerAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapter.MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view_customer, parent, false
        ))
    }

    override fun onBindViewHolder(holder: CustomerAdapter.MyViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(list: List<User>?) {
        this.list.clear()
        if (list != null) {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder(var binding: ItemViewCustomerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(data: User) {
            binding.data = data
        }

    }

}