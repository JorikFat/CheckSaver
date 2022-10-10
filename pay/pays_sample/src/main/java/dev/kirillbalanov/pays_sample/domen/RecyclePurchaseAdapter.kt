package dev.kirillbalanov.pays_sample.domen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.kirillbalanov.pays_sample.R
import dev.kirillbalanov.pays_sample.data.Purchase

class RecyclePurchaseAdapter() : RecyclerView.Adapter<RecyclePurchaseAdapter.PurchaseItemHolder>() {

    private val listPurchases = mutableListOf<Purchase>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseItemHolder {
        return PurchaseItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false))
    }

    override fun onBindViewHolder(holder: PurchaseItemHolder, position: Int) {
        val purchaseItem = listPurchases[position]
        if (purchaseItem.isValid()) {
            holder.tvProduct?.text ?: purchaseItem.product
            holder.tvPrice?.text ?: purchaseItem.price.toString()
            holder.tvQuantity?.text ?: purchaseItem.quantity.toString()
            holder.tvSummary?.text ?: purchaseItem.summary.toString()
        }
    }

    override fun getItemCount(): Int {
        return listPurchases.size
    }

    class PurchaseItemHolder(view: View): RecyclerView.ViewHolder(view){
        val tvProduct: TextView? = view.findViewById(R.id.tv_product)
        val tvPrice: TextView? = view.findViewById(R.id.tv_price)
        val tvQuantity: TextView? = view.findViewById(R.id.tv_quantity)
        val tvSummary: TextView? = view.findViewById(R.id.tv_summary)
    }

    constructor(purchase: Purchase) : this() {
        listPurchases.add(purchase)
    }
}