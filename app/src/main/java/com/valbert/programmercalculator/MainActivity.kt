package com.valbert.programmercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private val calculator: Calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun changeBase(view: View) {
        when(calculator.base) {
            "hex" -> {
                val button: Button = findViewById(R.id.hexButton)
                button.setBackgroundResource(0)
            }
            "dec" -> {
                val button: Button = findViewById(R.id.decButton)
                button.setBackgroundResource(0)
            }
            "oct" -> {
                val button: Button = findViewById(R.id.octButton)
                button.setBackgroundResource(0)
            }
            "bin" -> {
                val button: Button = findViewById(R.id.binButton)
                button.setBackgroundResource(0)
            }
        }

        calculator.base = when(view.tag) {
            "hex" -> "hex"
            "dec" -> "dec"
            "oct" -> "oct"
            "bin" -> "bin"
            else -> "Error"
        }

        view.setBackgroundResource(R.drawable.base_green)
    }
}