package com.e.mathematicalexpanston

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.e.mathematicalexpanston.binomilaexp.*
import com.e.mathematicalexpanston.factorial.FactorialActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnpositivewithconst(view: View) {
        val intent = Intent(this, BinomialExpActivity::class.java)
        startActivity(intent)
    }

    fun btnnegaitivewithconst(view: View) {
        val intent = Intent(this, BinomialExpForNegativeActivity::class.java)
        startActivity(intent)
    }

    fun btnpositivewithoutconst(view: View) {
        val intent = Intent(this, BinomialExpWithoutConstantActivity::class.java)
        startActivity(intent)
    }

    fun btnnegaitivewithoutconst(view: View) {
        val intent = Intent(this, BinomialExpForNegativeWithoutConstantActivity::class.java)
        startActivity(intent)
    }

    fun btnpositivewithdynamicconst(view: View) {
        val intent = Intent(this, BinomialExpWithDynamicConstantsActivity::class.java)
        startActivity(intent)
    }

    fun btnnegativewithdynamicconst(view: View) {
        val intent = Intent(this, BinomialExpForNegativeWithDynamicConstantsActivity::class.java)
        startActivity(intent)
    }

    fun btnwithdynamicdata(view: View) {
        val intent = Intent(this, BinomialExpWithDynamicDataActivity::class.java)
        startActivity(intent)
    }

    fun btnnegativewithdynamicdata(view: View) {
        val intent = Intent(this, BinomialExpForNegativeWithDynamicDataActivity::class.java)
        startActivity(intent)
    }

    fun btnnfactorial(view: View) {
        val intent = Intent(this, FactorialActivity::class.java)
        startActivity(intent)
    }

}