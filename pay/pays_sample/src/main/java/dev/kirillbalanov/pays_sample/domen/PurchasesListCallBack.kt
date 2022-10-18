package dev.kirillbalanov.pays_sample.domen

import androidx.recyclerview.widget.DiffUtil
import dev.kirillbalanov.pays_sample.data.Purchase

class PurchasesItemDiffCallBack: DiffUtil.ItemCallback<Purchase>() {

    override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
        return oldItem==newItem
    }
}