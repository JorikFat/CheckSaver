package dev.kirillbalanov.pays_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kirillbalanov.pays_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listShoppong.text =
                "Курица___213,65___1___213,65\n" +
                "Пакет___3,90___1___3,90\n" +
                "Телапия___169,99___1___169,99\n" +
                "Чечевица___57,99___1___57,99\n" +
                "Кефир___43,79___1___43,79\n" +
                "Скотч малярный___120___1___120"
    }
}