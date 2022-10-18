package dev.kirillbalanov.pays_sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.kirillbalanov.pays_sample.data.Purchase
import dev.kirillbalanov.pays_sample.databinding.ActivityMainBinding
import dev.kirillbalanov.pays_sample.domen.PurchaseListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var purchaseRecycleAdapter: PurchaseListAdapter
    private var purchase: Purchase = Purchase(0,"", 0.0f, 0.0f,0.0f) // it's for example, before the dialog isn't done
    private var purchaseList = mutableListOf<Purchase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        purchaseList.add(purchase)
        purchaseRecycleAdapter = PurchaseListAdapter()
        purchaseRecycleAdapter.submitList(purchaseList)
        val purchaseRecycleView: RecyclerView = binding.rcPushase
        purchaseRecycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        purchaseRecycleView.setHasFixedSize(true)
        purchaseRecycleView.adapter = purchaseRecycleAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.opt_add -> CreateDialogFragment
            .newInstance("Запись чека")
            .show(supportFragmentManager, CreateDialogFragment.TAG)
        }
        return super.onOptionsItemSelected(item)
    }
}