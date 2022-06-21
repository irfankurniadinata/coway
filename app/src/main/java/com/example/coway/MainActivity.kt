package com.example.coway

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.coway.core.BaseActivity
import com.example.coway.databinding.ActivityMainBinding
import com.example.coway.ui.activity.customer_list.CustomerListActivity
import com.example.coway.ui.activity.order_list.OrderListActivity
import com.example.coway.ui.activity.product_list.ProductListActivity
import com.example.coway.ui.adapter.CustomerAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var viewModel: MainViewModel

    override fun getResLayoutId(): Int = R.layout.activity_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.data = viewModel

        binding.sectionProduct.setOnClickListener {
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }

        binding.sectionCustomer.setOnClickListener {
            val intent = Intent(this, CustomerListActivity::class.java)
            startActivity(intent)
        }

        binding.sectionOrder.setOnClickListener {
            val intent = Intent(this, OrderListActivity::class.java)
            startActivity(intent)
        }
    }
}