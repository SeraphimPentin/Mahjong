package com.example.mahjong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mahjong.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }

    fun onClickEasy(view: View) {
        val intent = Intent(this, Recycle::class.java).apply {
            putExtra("easy", 4)
        }
        startActivity(intent)
    }

    fun onClickMiddle(view: View) {
        val intent = Intent(this, Recycle::class.java).apply {
            putExtra("middle", 6)
        }
        startActivity(intent)
    }

    fun onClickHard(view: View) {
        val intent = Intent(this, Recycle::class.java).apply {
            putExtra("hard", 10)
        }
        startActivity(intent)
    }


}
