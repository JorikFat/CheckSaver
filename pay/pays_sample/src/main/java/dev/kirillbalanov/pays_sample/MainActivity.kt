package dev.kirillbalanov.pays_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.kirillbalanov.pays_sample.data.Purchase
import dev.kirillbalanov.pays_sample.databinding.ActivityMainBinding
import dev.kirillbalanov.pays_sample.domen.RecyclePurchaseAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var purchaseRecycleAdapter: RecyclePurchaseAdapter
    private lateinit var purchase: Purchase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        purchaseRecycleAdapter = RecyclePurchaseAdapter(purchase)
        val purchaseRecycleView: RecyclerView = findViewById(R.id.rc_pushase)
        purchaseRecycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        purchaseRecycleView.setHasFixedSize(true)
        purchaseRecycleView.adapter = purchaseRecycleAdapter

        binding.btnAdd.setOnClickListener{
            CreateDialogFragment.newInstance("Запись чека", "Нужно внести данные").show(supportFragmentManager, CreateDialogFragment.TAG)
        }
    }
}