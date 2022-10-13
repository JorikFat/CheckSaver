package dev.kirillbalanov.pays_sample.domen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.kirillbalanov.pays_sample.data.Purchase
import dev.kirillbalanov.pays_sample.databinding.ItemPurchaseBinding
import androidx.recyclerview.widget.ListAdapter as ListAdapterPurchases

class PurchaseListAdapter: ListAdapterPurchases
    <Purchase, PurchaseListAdapter.PurchaseItemHolder>(PurchasesItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseItemHolder {
        return PurchaseItemHolder(ItemPurchaseBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PurchaseItemHolder, position: Int) {
        val purchaseItem = getItem(position)
        if (purchaseItem.isValid()) {
            holder.itemProduct.text = purchaseItem.product
            holder.itemPrice.text = purchaseItem.price.toString()
            holder.itemQuantity.text = purchaseItem.quantity.toString()
            holder.itemSummary.text = purchaseItem.summary.toString()
        }
    }

    class PurchaseItemHolder(binding: ItemPurchaseBinding): RecyclerView.ViewHolder(binding.root){
        val itemProduct: TextView = binding.tvProduct
        val itemPrice: TextView = binding.tvPrice
        val itemQuantity: TextView = binding.tvQuantity
        val itemSummary: TextView = binding.tvSummary
    }
}