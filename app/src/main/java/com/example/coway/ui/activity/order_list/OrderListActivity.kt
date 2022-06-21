package com.example.coway.ui.activity.order_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coway.R
import com.example.coway.core.BaseActivity
import com.example.coway.data.Order
import com.example.coway.databinding.ActivityOrderListBinding
import com.example.coway.ui.activity.order_detail.OrderDetailActivity
import com.example.coway.ui.adapter.AdapterClickListener
import com.example.coway.ui.adapter.OrderListAdapter
import com.example.coway.utils.NavigationView
import com.example.coway.utils.setupLinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class OrderListActivity : BaseActivity<ActivityOrderListBinding>() {

    private lateinit var viewModel : OrderListViewModel

    var orders: List<Order>? = null

    private val adapter = OrderListAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<Order> {
            override fun onItemClick(data: Order) {
                val intent = Intent(this@OrderListActivity, OrderDetailActivity::class.java)
                intent.putExtra("data", Gson().toJson(data))
                startActivity(intent)
            }

            override fun onViewClick(view: View, data: Order) {

            }

        }
    }

    override fun getResLayoutId(): Int = R.layout.activity_order_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        readLocalJsonOrder()
        initView()
    }

    private fun readLocalJsonOrder() {
        val rawJson = resources.openRawResource(R.raw.sample_order_list)
            .bufferedReader().use { it.readText() }
        val listType = object : TypeToken<ArrayList<Order>?>() {}.type
        orders = Gson().fromJson(rawJson, listType)
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(OrderListViewModel::class.java)
        binding.data = viewModel

        NavigationView(this).setupNavigationWithAction("Order") {
            finish()
        }

        binding.apply {
            rvOrder.setupLinearLayoutManager(LinearLayoutManager.VERTICAL)
            rvOrder.adapter = adapter
            adapter.setData(orders)
        }
    }
}