package com.example.coway.ui.activity.product_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.coway.R
import com.example.coway.core.BaseActivity
import com.example.coway.data.Product
import com.example.coway.databinding.ActivityProductListBinding
import com.example.coway.ui.activity.detail_product.DetailProductActivity
import com.example.coway.ui.adapter.AdapterClickListener
import com.example.coway.ui.adapter.ProductAdapter
import com.example.coway.utils.NavigationView
import com.example.coway.utils.setupGridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class ProductListActivity : BaseActivity<ActivityProductListBinding>() {

    private lateinit var viewModel: ProductListViewModel

    override fun getResLayoutId(): Int = R.layout.activity_product_list

    var product: List<Product>? = null

    private val adapter = ProductAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<Product> {
            override fun onItemClick(data: Product) {
                val intent = Intent(this@ProductListActivity, DetailProductActivity::class.java)
                intent.putExtra("data", Gson().toJson(data))
                startActivity(intent)
            }

            override fun onViewClick(view: View, data: Product) {

            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        readLocalJson()
        initView()
    }

    private fun readLocalJson() {
        val rawJson = resources.openRawResource(R.raw.sample_product)
            .bufferedReader().use { it.readText() }
        val listType = object : TypeToken<ArrayList<Product>?>() {}.type
        product = Gson().fromJson(rawJson, listType)
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        binding.data = viewModel

        NavigationView(this).setupNavigationWithAction("Produk") {
            finish()
        }

        binding.apply {
            rvProduct.setupGridLayoutManager(2)
            rvProduct.adapter = adapter
            adapter.setData(product)
        }
    }
}