package dev.kirillbalanov.pays_sample.data

data class Purchase (
    val product: String,
    val quantity: Float,
    val price: Float,
    val summary: Float
){
    fun isValid(): Boolean {
        return product.isNotEmpty() && quantity.toString().isNotEmpty() &&
                price.toString().isNotEmpty() && summary.toString().isNotEmpty()
    }
}