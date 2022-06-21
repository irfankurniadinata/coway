package com.example.coway.ui.activity.checkout

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coway.R
import com.example.coway.core.BaseActivity
import com.example.coway.data.PaymentMethod
import com.example.coway.data.Product
import com.example.coway.databinding.ActivityCheckoutBinding
import com.example.coway.ui.adapter.AdapterClickListener
import com.example.coway.ui.adapter.PaymentMethodAdapter
import com.example.coway.utils.NavigationView
import com.example.coway.utils.gone
import com.example.coway.utils.setImage
import com.example.coway.utils.setupLinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CheckoutActivity : BaseActivity<ActivityCheckoutBinding>() {

    private lateinit var viewModel: CheckoutViewModel

    override fun getResLayoutId(): Int = R.layout.activity_checkout

    var data: Product? = null
    var qty: Int? = 0

    private val adapter = PaymentMethodAdapter(arrayListOf()).apply {
        listener = object : AdapterClickListener<PaymentMethod> {
            override fun onItemClick(data: PaymentMethod) {
                selectedPayment = data.paymentMethodName
                notifyDataSetChanged()
            }

            override fun onViewClick(view: View, data: PaymentMethod) {

            }

        }
    }

    private var paymentMethod: List<PaymentMethod>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        readLocalJsonPaymentMethod()
        initView()
    }

    private fun readLocalJsonPaymentMethod() {
        val rawJson = resources.openRawResource(R.raw.sample_payment_method)
            .bufferedReader().use { it.readText() }
        val listType = object : TypeToken<ArrayList<PaymentMethod>?>() {}.type
        paymentMethod = Gson().fromJson(rawJson, listType)
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(CheckoutViewModel::class.java)
        binding.data = viewModel

        NavigationView(this).setupNavigationWithAction("Checkout") {
            finish()
        }

        data = Product.objectFromData(intent.getStringExtra("data"))
        qty = intent.getIntExtra("qty", 0)

        binding.apply {
            tvBasePrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            rvPaymentMethod.setupLinearLayoutManager(LinearLayoutManager.VERTICAL)
            rvPaymentMethod.adapter = adapter
            adapter.setData(paymentMethod)
        }

        setupData(data, qty)
    }

    private fun setupData(data: Product?, qty: Int?) {
        binding.apply {
            imageProduct.setImage(data?.image)
            tvProductName.text = data?.name
            if (data?.basePrice == data?.discountedPrice) {
                tvBasePrice.gone()
                tvDiscountPrice.text = data?.discountedPrice
            } else {
                tvBasePrice.text = data?.basePrice
                tvDiscountPrice.text = data?.discountedPrice
            }
            tvQuantity.text = "x $qty"
        }
    }
}