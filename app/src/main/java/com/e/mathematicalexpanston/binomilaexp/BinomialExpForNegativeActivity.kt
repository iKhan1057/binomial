package com.e.mathematicalexpanston.binomilaexp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.e.mathematicalexpanston.BaseActivity
import com.e.mathematicalexpanston.R

class BinomialExpForNegativeActivity : BaseActivity() {
    lateinit var binomial_exp_txt_initial: TextView
    lateinit var binomial_exp_et_input_n: EditText
    lateinit var binomial_exp_txt_expanded: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binomial_exp)
        initviews()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun initviews() {
        binomial_exp_txt_expanded = findViewById(R.id.binomial_exp_txt_expanded)
        binomial_exp_txt_initial = findViewById(R.id.binomial_exp_txt_initial)

        val ast = "(ax-by)<sup><small>n</small></sup>"
        binomial_exp_txt_initial.text = convertToHTML("<html>$ast</html>")

        binomial_exp_et_input_n = findViewById(R.id.binomial_exp_et_input_n)

        val binomial_exp_btn_gen: Button = findViewById(R.id.binomial_exp_btn_gen)
        binomial_exp_btn_gen.setOnClickListener {
            if (binomial_exp_et_input_n.text.toString().trim().isNotEmpty()) {
                binomial_exp_txt_initial.text = convertToHTML(
                    "<html>(ax-by)<sup><small>${
                        binomial_exp_et_input_n.text.toString().trim()
                    }</small></sup></html>"
                )
                binomial_exp_txt_expanded.text = convertToHTML("<html>${expandBinomial()}</html>")
            }
        }
    }

    private fun expandBinomial(): String {
        val n = (binomial_exp_et_input_n.text.toString()).toLong()
        val ast: StringBuilder = java.lang.StringBuilder()

        if (n == 0L)
            return "1"
        else {
            for (i in 0..n) {
                if (ast.isNotEmpty()) {
                    ast.append(" <font color=#FFFFFF>${createSign(i)}</font> " + nextnum(n, i))
                } else
                    ast.append(nextnum(n, i))
            }
            return ast.toString()
        }
    }

    private fun createSign(i: Long): String {
        return if (i % 2 == 0L) "+"
        else "-"
    }

    private fun nextnum(u: Long, i: Long): String {
        return "${coeff(u, i)}${varia(u, i)}"
    }

    private fun varia(u: Long, i: Long): Any {
        val n = u - i
        return "${varaX(n)}${varY(i)}"
    }

    private fun varY(i: Long): String {
        if (i == 1L)
            return "<font color='red'>b</font><font color='green'>y</font>"
        else if (i > 1)
            return "<font color='red'>b<sup><small>$i</small></sup></font><font color='green'>y<sup><small>$i</small></sup></font>"
        return ""
    }

    private fun varaX(n: Long): String {
        if (n == 1L)
            return "<font color='red'>a</font><font color='green'>x</font>"
        else if (n > 1)
            return "<font color='red'>a<sup><small>$n</small></sup></font><font color='green'>x<sup><small>$n</small></sup></font>"
        return ""
    }

    private fun coeff(u: Long, i: Long): Any {
        val z = (calculateFactorial(u) / (calculateFactorial(u - i) * (calculateFactorial(i))))

        return if (z == 1L) ""
        else "<font color='white'>$z</font>"
    }
}