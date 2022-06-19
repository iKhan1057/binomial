package com.e.mathematicalexpanston

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spanned
import androidx.core.text.HtmlCompat

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }


    open fun calculateFactorial(no: Long): Long {
        var num: Long = no
        if (num == 0L || num == 1L) return 1
        var count = num - 1
        do {
            num *= count
            count -= 1
        } while (count > 0)
        return num
    }


    open fun convertToHTML(html: String): Spanned {
        return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY);
    }
}