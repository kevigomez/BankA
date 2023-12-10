package com.example.banka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etInitialBalance: EditText
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInitialBalance = findViewById(R.id.etInitialBalance)
        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            val initialBalance = etInitialBalance.text.toString().toDoubleOrNull()
            if (initialBalance != null) {
                val intent = Intent(this, Operaciones::class.java)
                intent.putExtra("initialBalance", initialBalance)
                startActivity(intent)
            } else {
                // Manejar el caso en que la entrada no es válida
            }
        }
    }
}
