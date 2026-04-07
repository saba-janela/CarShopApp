package com.example.carshopapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class PaymentActivity : AppCompatActivity() {

    private var carPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val carName = intent.getStringExtra("CAR_NAME") ?: "Unknown"
        carPrice = intent.getDoubleExtra("CAR_PRICE", 0.0)
        val carImage = intent.getIntExtra("CAR_IMAGE", 0)

        val tvItemName = findViewById<TextView>(R.id.tvItemName)
        val tvItemPrice = findViewById<TextView>(R.id.tvItemPrice)
        val imgThumb = findViewById<ImageView>(R.id.imgItemThumb)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val radioStandard = findViewById<RadioButton>(R.id.radioStandard)
        val radioExpress = findViewById<RadioButton>(R.id.radioExpress)
        val rowStandard = findViewById<LinearLayout>(R.id.rowStandard)
        val rowExpress = findViewById<LinearLayout>(R.id.rowExpress)
        val btnPay = findViewById<Button>(R.id.btnPay)

        tvItemName.text = carName
        tvItemPrice.text = formatPrice(carPrice)
        if (carImage != 0) imgThumb.setImageResource(carImage)

        updateTotal(tvTotal, radioExpress.isChecked)

        rowStandard.setOnClickListener {
            radioStandard.isChecked = true
            radioExpress.isChecked = false
            updateTotal(tvTotal, false)
        }

        rowExpress.setOnClickListener {
            radioExpress.isChecked = true
            radioStandard.isChecked = false
            updateTotal(tvTotal, true)
        }

        btnPay.setOnClickListener {
            startActivity(Intent(this, DoneActivity::class.java))
        }
    }

    private fun updateTotal(tvTotal: TextView, isExpress: Boolean) {
        val discounted: Double = carPrice * 0.95
        val shipping: Double = if (isExpress) 1700.0 else 0.0
        val total: Double = discounted + shipping
        tvTotal.text = formatPrice(total)
    }

    private fun formatPrice(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        formatter.maximumFractionDigits = 0
        return formatter.format(amount)
    }
}
