package com.example.androidndkcalculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidndkcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var data = ""
    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)
        binding.btnThree.setOnClickListener(this)
        binding.btnFour.setOnClickListener(this)
        binding.btnFive.setOnClickListener(this)
        binding.btnSix.setOnClickListener(this)
        binding.btnSeven.setOnClickListener(this)
        binding.btnEight.setOnClickListener(this)
        binding.btnNine.setOnClickListener(this)
        binding.btnZero.setOnClickListener(this)

        binding.btnPlus.setOnClickListener(this)
        binding.btnMultiple.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
        binding.btnDivide.setOnClickListener(this)

        binding.btnClearAll.setOnClickListener(this)
        binding.btnEqual.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_one -> {
                data += "1"
            }

            R.id.btn_two -> {
                data += "2"
            }

            R.id.btn_three -> {
                data += "3"
            }

            R.id.btn_four -> {
                data += "4"
            }

            R.id.btn_five -> {
                data += "5"
            }

            R.id.btn_six -> {
                data += "6"
            }

            R.id.btn_seven -> {
                data += "7"
            }

            R.id.btn_eight -> {
                data += "8"
            }

            R.id.btn_nine -> {
                data += "9"
            }

            R.id.btn_zero -> {
                if (data.isNotEmpty()) data += "0"
            }
            // add , mul etc button
            R.id.btn_plus -> {
                if (data.isNotEmpty()) data += "+"
            }

            R.id.btn_multiple -> {
                if (data.isNotEmpty()) data += "x"
            }

            R.id.btn_minus -> {
                if (data.isNotEmpty()) data += "-"
            }

            R.id.btn_divide -> {
                if (data.isNotEmpty()) data += "/"
            }
            // Equal
            R.id.btn_equal -> {
                if (data.isNotEmpty()) getTotal()
            }
            // Clear all
            R.id.btn_clear_all -> {
                data = ""
                binding.tvAns.text = ""
            }
        }
        binding.tvData.text = data
    }

    private fun getTotal() {
        var ans: Int = 0
        val symbols =
            data.replace(("[^+x/-]").toRegex(), "").toCharArray() // symbols like + x - / etc
        val numbers = data.split("+", "x", "/", "-") // it will be a numbers
        var isError = false

        try {
            ans = doCalculation(symbols, 0, numbers[0].toInt(), numbers)
        } catch (e: Exception) {
            isError = true
        }

        for (index in 1 until symbols.size) {
            try {
                ans = doCalculation(symbols, index, ans, numbers)
            } catch (e: Exception) {
                isError = true
                e.printStackTrace()
            }
        }
        if (!isError) {
            binding.tvAns.text = ans.toString()
            data = ans.toString()
            binding.tvData.text = data
        } else {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doCalculation(symbols: CharArray, index: Int, ans: Int, intVal: List<String>): Int {
        var ans1 = ans
        when {
            symbols[index].toString() == "+" -> {
                ans1 = calculator.add(ans1, intVal[index + 1].toInt())
            }

            symbols[index].toString() == "x" -> {
                ans1 = calculator.multiply(ans1, intVal[index + 1].toInt())
            }

            symbols[index].toString() == "-" -> {
                ans1 = calculator.subtracation(ans1, intVal[index + 1].toInt())
            }

            symbols[index].toString() == "/" -> {
                ans1 = calculator.division(ans1, intVal[index + 1].toInt())
            }
        }
        return ans1
    }
}