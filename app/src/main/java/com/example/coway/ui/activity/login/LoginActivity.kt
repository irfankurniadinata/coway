package com.example.coway.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.coway.MainActivity
import com.example.coway.R
import com.example.coway.core.BaseActivity
import com.example.coway.databinding.ActivityLoginBinding
import com.example.coway.ui.activity.register.RegisterActivity
import com.example.coway.utils.DBHelper
import com.example.coway.utils.makeLinks

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var databaseHelper: DBHelper

    override fun getResLayoutId(): Int = R.layout.activity_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.data = viewModel

        databaseHelper = DBHelper(this)

        binding.btnLogin.setOnClickListener {
            loginDataToSQLLite()
        }

        binding.btnRegister.makeLinks(
            Pair("Daftar", View.OnClickListener {
                val i = Intent(this, RegisterActivity::class.java)
                startActivity(i)
            })
        )
    }

    private fun loginDataToSQLLite() {
        val email = viewModel.emailField.get().toString().trim()
        val password = viewModel.passwordField.get().toString().trim()
        if (email.isEmpty() || password.isEmpty()) {
            showToastDanger("Data tidak boleh kosong")
            return
        } else {
            if (databaseHelper.checkUser(email, password)) {
                showToastInfo("Login Berhasil")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                showToastInfo("Email atau Password Salah")
            }
        }
    }
}