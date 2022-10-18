package dev.kirillbalanov.pays_sample.data

data class Purchase (
    val id: Int,
    val product: String,
    val quantity: Float,
    val price: Float,
    val summary: Float
){
    fun isValid(): Boolean {
        return product.equals(null) && quantity.equals(null) &&
                price.equals(null) && summary.equals(null)
    }
}