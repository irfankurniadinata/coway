package com.example.coway.ui.activity.order_detail

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.coway.R
import com.example.coway.core.BaseActivity
import com.example.coway.data.Order
import com.example.coway.databinding.ActivityOrderDetailBinding
import com.example.coway.utils.NavigationView
import com.example.coway.utils.setImage

class OrderDetailActivity : BaseActivity<ActivityOrderDetailBinding>() {

    private lateinit var viewModel: OrderDetailViewModel

    override fun getResLayoutId(): Int = R.layout.activity_order_detail

    var order : Order? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(OrderDetailViewModel::class.java)
        binding.data = viewModel

        NavigationView(this).setupNavigationWithAction("Order Detail") {
            finish()
        }

        order = Order.objectFromData(intent.getStringExtra("data"))

        binding.apply {
            tvStatusOrder.text = order?.status
            when(order?.status) {
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
            productImage.setImage(order?.image)
            tvProductName.text = order?.name
            tvTotalPayment.text = "Total Pembayaran : ${order?.totalPayment}"
            tvQuantity.text = "Quantity : ${order?.quantity}"
            tvPaymentMethod.text = order?.paymentMethod
        }

    }
}