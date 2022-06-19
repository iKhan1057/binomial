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

class BinomialExpWithDynamicConstantsActivity : BaseActivity() {
    lateinit var binomial_exp_txt_initial: TextView
    lateinit var binomial_exp_et_input_a: EditText
    lateinit var binomial_exp_et_input_b: EditText
    lateinit var binomial_exp_et_input_n: EditText
    lateinit var binomial_exp_txt_expanded: TextView
    var a: Long = 1L
    var b: Long = 1L

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

        val ast = "(ax + by)<sup><small>n</small></sup>"
        binomial_exp_txt_initial.text = convertToHTML("<html>$ast</html>")

        binomial_exp_et_input_n = findViewById(R.id.binomial_exp_et_input_n)
        binomial_exp_et_input_a = findViewById(R.id.binomial_exp_et_input_a)
        binomial_exp_et_input_b = findViewById(R.id.binomial_exp_et_input_b)

        val binomial_exp_btn_gen: Button = findViewById(R.id.binomial_exp_btn_gen)
        binomial_exp_btn_gen.setOnClickListener {
            if (binomial_exp_et_input_n.text.toString().trim().isNotEmpty()) {
                a = if (binomial_exp_et_input_a.text.toString().isNotEmpty())
                    binomial_exp_et_input_a.text.toString().toLong()
                else
                    1L

                b = if (binomial_exp_et_input_b.text.toString().isNotEmpty())
                    binomial_exp_et_input_b.text.toString().toLong()
                else
                    1L

                binomial_exp_txt_initial.text = convertToHTML(
                    "<html>${setConstants()}<sup><small>${
                        binomial_exp_et_input_n.text.toString().trim()
                    }</small></sup></html>"
                )
                binomial_exp_txt_expanded.text = convertToHTML("<html>${expandBinomial()}</html>")
            }
        }
    }

    private fun setConstants(): String {
        var s = java.lang.StringBuilder()
        s.clear()
        s.append("(")
        if (binomial_exp_et_input_a.text.toString().isNotEmpty()) {
            s = if (a > 1)
                s.append("${a}x")
            else
                s.append("x")
        } else {
            s.append("ax")
        }

        s.append(" + ")

        if (binomial_exp_et_input_b.text.toString().isNotEmpty()) {
            s = if (b > 1)
                s.append("${b}y")
            else
                s.append("y")
        } else {
            s.append("by")
        }

        s.append(")")
        return s.toString()
    }

    private fun expandBinomial(): String {
        val n = (binomial_exp_et_input_n.text.toString()).toLong()
        val ast: StringBuilder = java.lang.StringBuilder()

        if (n == 0L)
            return "1"
        else {
            for (i in 0..n) {
                if (ast.isNotEmpty()) {
                    ast.append(" <font color=#FFBB86>+</font> " + nextnum(n, i))
                } else
                    ast.append(nextnum(n, i))
            }
            return ast.toString()
        }
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
            return "<font color='red'>${checkCoeff("b", i)}</font><font color='green'>y</font>"
        else if (i > 1)
            return "<font color='red'>${
                checkCoeff(
                    "b",
                    i
                )
            }</font><font color='green'>y<sup><small>$i</small></sup></font>"
        return ""
    }

    private fun varaX(n: Long): String {
        if (n == 1L)
            return "<font color='red'>${checkCoeff("a", n)}</font><font color='green'>x</font>"
        else if (n > 1)
            return "<font color='red'>${
                checkCoeff(
                    "a",
                    n
                )
            }</font><font color='green'>x<sup><small>$n</small></sup></font>"
        return ""
    }

    private fun checkCoeff(u: String, p: Long): String {
        var o = ""
        if (u.equals("a")) {
            if (binomial_exp_et_input_a.text.toString().isEmpty()) {
                o = if (p > 1)
                    "a<sup><small>$p</small></sup>"
                else
                    "a"
            }
        } else {
            if (binomial_exp_et_input_b.text.toString().isEmpty()) {
                o = if (p > 1)
                    "b<sup><small>$p</small></sup>"
                else
                    "b"
            }
        }
        return o
    }

    private fun coeff(u: Long, i: Long): Any {
        var z = (calculateFactorial(u) / (calculateFactorial(u - i) * (calculateFactorial(i))))
        if (binomial_exp_et_input_a.text.toString().isNotEmpty()) {
            z *= Math.pow(a.toDouble(), (u - i).toDouble()).toLong()
        }

        if (binomial_exp_et_input_b.text.toString().isNotEmpty()) {
            z *= Math.pow(b.toDouble(), (i).toDouble()).toLong()
        }

        return if (z == 1L) ""
        else "<font color='white'>$z</font>"
    }
}