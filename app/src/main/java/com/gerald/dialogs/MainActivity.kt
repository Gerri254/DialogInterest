package com.gerald.dialogs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonBasic: Button = findViewById(R.id.buttonBasic)
        val buttonCustom: Button = findViewById(R.id.buttonCustom)

        buttonBasic.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure you want to delete")
            builder.setMessage("Once you delete this photo you wont be able to recover")
            builder.setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            //builder.create().show()
            //make sure it is not cancellable
            val alert: AlertDialog = builder.create()
            alert.setCancelable(false)
            alert.show()

        }
        buttonCustom.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val layout = layoutInflater.inflate(R.layout.custom_dialog, null)
            builder.setTitle("Interest Calculator")
            val editPrincipal: EditText = layout.findViewById(R.id.editPrincipal)
            val editRate: EditText = layout.findViewById(R.id.editRate)
            val editPeriod: EditText = layout.findViewById(R.id.editPeriod)


            builder.setView(layout)
            builder.setPositiveButton("Calculate") { _, _ ->
                val principal = editPrincipal.text.toString().trim().toDoubleOrNull()
                val period = editPeriod.text.toString().trim().toDoubleOrNull()
                val rate = editRate.text.toString().trim().toDoubleOrNull()

                if (principal != null && period != null && rate != null) {
                    calculateInterest(principal, rate, period)
                } else {
                    Toast.makeText(this, "Invalid values", Toast.LENGTH_SHORT).show()
                }

//                if(principal.isNotEmpty()&& period.isNotEmpty() && rate.isNotEmpty()){
//                    calculateInterest(principal.toDouble, rate.toDouble, period.toDouble)
//                }
            }
            builder.show()
        }

    }

    fun calculateInterest(principal: Double, rate: Double, period: Double) {
        var amount = principal * Math.pow((1 + rate / 100), period)
        Toast.makeText(this, "KES $amount", Toast.LENGTH_LONG).show()
        Log.d("INTEREST", "$amount")
    }
}