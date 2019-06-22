package com.anandaikhsan.centinews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.anandaikhsan.centinews.ui.home.Home

class MainActivity : AppCompatActivity() {
    private val DELAY: Long = 3000
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        handler.postDelayed(runnable, DELAY)
    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }

    internal val runnable: Runnable = Runnable{
        if(!isFinishing){
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }
    }
}
