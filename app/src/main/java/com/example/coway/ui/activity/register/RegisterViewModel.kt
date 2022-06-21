package com.example.coway.ui.activity.register

import androidx.databinding.ObservableField
import com.example.coway.core.BaseViewModel

class RegisterViewModel: BaseViewModel() {
    /* Define the live data here*/
    val emailField = ObservableField<String>()
    val passwordField = ObservableField<String>()
    val confirmPasswordField = ObservableField<String>()
    val nameField = ObservableField<String>()
}