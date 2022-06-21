package com.example.coway.ui.activity.detail_product

import androidx.lifecycle.MutableLiveData
import com.example.coway.core.BaseViewModel

class DetailProductViewModel: BaseViewModel() {

    /* Define the live data here*/
    var productQuantity: MutableLiveData<Int> = MutableLiveData(1)
    var maxQuantity: Int = 0
    var minQuantity: Int = 1


    fun addQuantity() {
        val qty: Int = productQuantity.value ?: 0
        if (qty < maxQuantity) {
            productQuantity.value = productQuantity.value?.plus(1)
        }
    }

    fun minQuantity() {
        val qty: Int = productQuantity.value ?: 0
        if (qty > minQuantity) {
            productQuantity.value = productQuantity.value?.minus(1)
        }
    }
}