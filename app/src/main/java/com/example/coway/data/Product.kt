package com.example.coway.data

import com.google.gson.Gson

data class Product(
    var name: String? = null,
    var basePrice: String? = null,
    var discountedPrice: String? = null,
    var image: String? = null,
    var description: String? = null,
    var quantity: Int? = null
) {
    companion object {
        fun objectFromData(str: String?): Product? {
            return try {
                Gson().fromJson(str, Product::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }
}
