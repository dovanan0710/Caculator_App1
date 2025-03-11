package com.example.caculator_app1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private var currentInput = ""
    private var firstNumber = 0
    private var secondNumber = 0
    private var operator = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        // Gán sự kiện cho các nút số
        val numberButtons = listOf(
            R.id.button5, R.id.button6, R.id.button7,  // 7, 8, 9
            R.id.button9, R.id.button10, R.id.button11, // 4, 5, 6
            R.id.button13, R.id.button14, R.id.button15, // 1, 2, 3
            R.id.button18  // 0
        )
        for (buttonId in numberButtons) {
            findViewById<Button>(buttonId).setOnClickListener {
                val buttonText = (it as Button).text.toString()
                handleNumberInput(buttonText)
            }
        }

        // Gán sự kiện cho các nút phép toán
        findViewById<Button>(R.id.button4).setOnClickListener { handleOperator("/") }
        findViewById<Button>(R.id.button8).setOnClickListener { handleOperator("x") }
        findViewById<Button>(R.id.button12).setOnClickListener { handleOperator("-") }
        findViewById<Button>(R.id.button16).setOnClickListener { handleOperator("+") }

        // Nút dấu bằng
        findViewById<Button>(R.id.button20).setOnClickListener { calculateResult() }

        // Nút C (Clear)
        findViewById<Button>(R.id.button2).setOnClickListener { clearAll() }

        // Nút CE (Xóa ký tự cuối)
        findViewById<Button>(R.id.button1).setOnClickListener { clearEntry() }

        // Nút Backspace (Xóa một số)
        findViewById<Button>(R.id.button3).setOnClickListener { removeLastDigit() }

        // Nút đổi dấu
        findViewById<Button>(R.id.button17).setOnClickListener { toggleSign() }
    }
    private fun handleNumberInput(number: String) {
        if (currentInput == "0") {
            currentInput = number
        } else {
            currentInput += number
        }
        textView.text = currentInput
    }
    private fun handleOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toInt()
            operator = op
            currentInput = ""
            textView.text = op
        }
    }
    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            secondNumber = currentInput.toInt()
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "x" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0) firstNumber / secondNumber else "Lỗi"
                else -> "Lỗi"
            }
            textView.text = result.toString()
            currentInput = result.toString()
            operator = ""
        }
    }
    private fun clearAll() {
        currentInput = ""
        firstNumber = 0
        secondNumber = 0
        operator = ""
        textView.text = "0"
    }
    private fun clearEntry() {
        currentInput = ""
        textView.text = "0"
    }
    private fun removeLastDigit() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            textView.text = if (currentInput.isEmpty()) "0" else currentInput
        }
    }
    private fun toggleSign() {
        if (currentInput.isNotEmpty() && currentInput != "0") {
            currentInput = if (currentInput.startsWith("-")) {
                currentInput.substring(1)
            } else {
                "-$currentInput"
            }
            textView.text = currentInput
        }
    }





}