package com.example.coway.data

import com.google.gson.Gson

data class Order(
    val name: String? = null,
    val totalPayment: String? = null,
    val image: String? = null,
    val quantity: String? = null,
    val status: String? = null,
    val paymentMethod: String? = null
) {
    companion object {
        fun objectFromData(str: String?): Order? {
            return try {
                Gson().fromJson(str, Order::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }
}
