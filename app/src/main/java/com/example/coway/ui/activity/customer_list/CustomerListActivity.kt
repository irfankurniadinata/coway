package com.example.coway.ui.activity.customer_list

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coway.R
import com.example.coway.core.BaseActivity
import com.example.coway.data.User
import com.example.coway.databinding.ActivityCustomerListBinding
import com.example.coway.ui.adapter.CustomerAdapter
import com.example.coway.utils.DBHelper
import com.example.coway.utils.NavigationView
import com.example.coway.utils.addTextWatcher
import com.example.coway.utils.setupLinearLayoutManager
import android.text.Editable

import android.text.TextWatcher




class CustomerListActivity : BaseActivity<ActivityCustomerListBinding>() {

    override fun getResLayoutId(): Int = R.layout.activity_customer_list

    private lateinit var viewModel: CustomerListViewModel

    private lateinit var databaseHelper: DBHelper
    var user: List<User>? = null

    private val adapter = CustomerAdapter(arrayListOf())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(CustomerListViewModel::class.java)
        binding.data = viewModel
        NavigationView(this).setupNavigationWithAction("Customer") {
            finish()
        }

        /*get customer*/
        databaseHelper = DBHelper(this)
        user = databaseHelper.getCustomer()

        binding.apply {
            rvCustomer.adapter = adapter
            rvCustomer.setupLinearLayoutManager(LinearLayoutManager.VERTICAL)
            setDataCustomer()
        }

        searchCustomer()
    }

    private fun setDataCustomer(name: String? = null) {
        if (name == null) {
            adapter.setData(user)
        } else {
            val filterUser = mutableListOf<User>()
            user?.forEach {
                if (it.name?.contains(name) == true) {
                    filterUser.add(it)
                }
            }
            adapter.setData(filterUser)
        }
    }

    private fun searchCustomer() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                setDataCustomer(s.toString())
            }
        })
    }
}