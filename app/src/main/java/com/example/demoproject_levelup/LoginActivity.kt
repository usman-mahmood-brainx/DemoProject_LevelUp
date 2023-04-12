package com.example.demoproject_levelup

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val spannableString = SpannableString(getString(R.string.signup))
        spannableString.setSpan(ForegroundColorSpan(Color.BLACK), 0, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(getColor(R.color.blue2)), 17,getString(R.string.signup).length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView = findViewById<TextView>(R.id.not_a_member)
        textView.text = spannableString
    }
}