package com.example.coway.ui.activity.login

import androidx.databinding.ObservableField
import com.example.coway.core.BaseViewModel

class LoginViewModel: BaseViewModel() {

    /* Define the live data here*/
    val emailField = ObservableField<String>()
    val passwordField = ObservableField<String>()


}