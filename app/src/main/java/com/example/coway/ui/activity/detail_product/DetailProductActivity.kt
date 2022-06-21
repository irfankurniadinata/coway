package com.example.coway.ui.activity.detail_product

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.coway.R
import com.example.coway.core.BaseActivity
import com.example.coway.data.Product
import com.example.coway.databinding.ActivityDetailProductBinding
import com.example.coway.ui.activity.checkout.CheckoutActivity
import com.example.coway.utils.NavigationView
import com.example.coway.utils.gone
import com.example.coway.utils.setImage
import com.google.gson.Gson

class DetailProductActivity : BaseActivity<ActivityDetailProductBinding>() {

    private lateinit var viewModel: DetailProductViewModel

    override fun getResLayoutId(): Int = R.layout.activity_detail_product

    var data: Product? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        observerQty()
    }

    private fun observerQty() {
        viewModel.productQuantity.observe(this, { qty ->
            binding.btnQtyMin.isEnabled = qty > viewModel.minQuantity
            binding.btnQtyPlus.isEnabled = qty < viewModel.maxQuantity

        })
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(DetailProductViewModel::class.java)
        binding.data = viewModel

        NavigationView(this).setupNavigationWithAction("Detail Produk") {
            finish()
        }

        data = Product.objectFromData(intent.getStringExtra("data"))
        setupQty()

        binding.tvBasePrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        binding.btnQtyMin.setOnClickListener {
            viewModel.minQuantity()
        }

        binding.btnQtyPlus.setOnClickListener {
            viewModel.addQuantity()
        }

        binding.btnAddToCart.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            intent.putExtra("data", Gson().toJson(data))
            intent.putExtra("qty", viewModel.productQuantity.value)
            startActivity(intent)
        }

        setupData(data)

    }

    private fun setupData(data: Product?) {
        binding.apply {
            image.setImage(data?.image)
            tvProductName.text = data?.name
            tvDescriptionProduct.text = data?.description
            if (data?.basePrice == data?.discountedPrice) {
                tvBasePrice.gone()
                tvDiscountPrice.text = data?.discountedPrice
            } else {
                tvBasePrice.text = data?.basePrice
                tvDiscountPrice.text = data?.discountedPrice
            }
        }
    }

    private fun setupQty() {
        viewModel.maxQuantity = data?.quantity ?: 0
        viewModel.minQuantity = 1
        viewModel.productQuantity.value = 1
    }
}