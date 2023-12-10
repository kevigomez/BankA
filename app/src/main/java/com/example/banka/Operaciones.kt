package com.example.banka

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Operaciones : AppCompatActivity() {

    private var balance = 0.0
    private lateinit var tvBalance: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operations)

        val initialBalance = intent.getDoubleExtra("initialBalance", 0.0)
        balance = initialBalance

        tvBalance = findViewById(R.id.tvBalance)
        val btnDeposit: Button = findViewById(R.id.btnDeposit)
        val btnWithdraw: Button = findViewById(R.id.btnWithdraw)

        btnDeposit.setOnClickListener { showInputDialog(true) }
        btnWithdraw.setOnClickListener { showInputDialog(false) }

        updateBalance()
    }

    private fun showInputDialog(isDeposit: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (isDeposit) "Depositar" else "Retirar")


        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Aceptar") { _, _ ->

            val amountText = input.text.toString()
            val amount = if (amountText.isNotEmpty()) amountText.toDouble() else 0.0


            if (isDeposit) {
                deposit(amount)
            } else {
                withdraw(amount)
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun deposit(amount: Double) {
        balance += amount
        updateBalance()
    }

    private fun withdraw(amount: Double) {
        if (balance >= amount) {
            balance -= amount
            updateBalance()
        } else {
            showErrorMessage("La cantidad a retirar no es válida o excede el saldo actual.")
        }
    }

    private fun updateBalance() {
        tvBalance.text = "Saldo: $$balance"
    }

    private fun showErrorMessage(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}

