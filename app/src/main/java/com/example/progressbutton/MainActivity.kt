package com.example.progressbutton

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var isProgressVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_button1.setOnClickListener {
            if (isProgressVisible) {
                progress_button1.stopProgress()
                isProgressVisible = false
            } else {
                progress_button1.startProgress()
                isProgressVisible = true
            }
        }
    }
}
