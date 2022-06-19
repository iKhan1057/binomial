package com.e.mathematicalexpanston.factorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.e.mathematicalexpanston.BaseActivity
import com.e.mathematicalexpanston.R

class FactorialActivity : BaseActivity() {
    lateinit var factorial_txt_initial: TextView
    lateinit var factorial_et_input_n: EditText
    lateinit var factorial_txt_result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factorial)
        initViews()
    }

    private fun initViews() {
        factorial_txt_initial = findViewById(R.id.factorial_txt_initial)
        factorial_et_input_n = findViewById(R.id.factorial_et_input_n)
        factorial_txt_result = findViewById(R.id.factorial_txt_result)

        "n!".also { factorial_txt_initial.text = it }

        val factorial_btn_gen: Button = findViewById(R.id.factorial_btn_gen)
        factorial_btn_gen.setOnClickListener {
            if (factorial_et_input_n.text.toString().trim().isNotEmpty()) {
                "${
                    factorial_et_input_n.text.toString().trim()
                }!".also { factorial_txt_initial.text = it }
                factorial_txt_result.text =
                    "${calculateFactorial(factorial_et_input_n.text.toString().trim().toLong())}"
            }
        }
    }
}