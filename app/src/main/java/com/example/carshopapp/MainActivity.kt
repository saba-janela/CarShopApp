package com.example.carshopapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<android.view.View>(R.id.cardBmw).setOnClickListener {
            openPayment("BMW M3 (F80 generation)", 38000.0, R.drawable.bmw_m3)
        }

        findViewById<android.view.View>(R.id.cardMercedes).setOnClickListener {
            openPayment("Mercedes-Benz CLA-Class (Second Generation)", 46400.0, R.drawable.mercedes_cla)
        }

        findViewById<android.view.View>(R.id.cardPorsche).setOnClickListener {
            openPayment("Porsche 911 GT3 RS (991.1 Generation)", 189000.0, R.drawable.porsche_911)
        }

        findViewById<android.view.View>(R.id.cardFerrari).setOnClickListener {
            openPayment("Ferrari 488 Spider", 260000.0, R.drawable.ferrari_488)
        }
    }

    private fun openPayment(name: String, price: Double, image: Int) {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("CAR_NAME", name)
        intent.putExtra("CAR_PRICE", price)
        intent.putExtra("CAR_IMAGE", image)
        startActivity(intent)
    }
}
