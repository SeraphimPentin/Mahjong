package com.example.mahjong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class Recycle : AppCompatActivity() {

    var pairsCount: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)

        val actionBar = supportActionBar
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)


        val i = intent
        if (i != null) pairsCount = i.getIntExtra("easy", 0)
        if (pairsCount == 0) pairsCount = i.getIntExtra("middle", 0)
        if (pairsCount == 0) pairsCount = i.getIntExtra("hard", 10)

        val base = arrayListOf(
            CardData("\uD83D\uDCA9"),
            CardData("\uD83D\uDE0D"),
            CardData("\uD83E\uDD18"),
            CardData("\uD83D\uDC36"),
            CardData("\uD83C\uDF0D"),
            CardData("\uD83D\uDC12"),
            CardData("\uD83D\uDC27"),
            CardData("\uD83E\uDD5D"),
            CardData("\uD83D\uDE97"),
            CardData("\uD83E\uDD51")
        )
        val data = arrayListOf<CardData>()
        for (i in 0 until pairsCount!!) {
            data += base[i]
            data += base[i]
        }

        val data2 = arrayListOf<CardData>()
        for (i in 0 until data.size) {
            val c = data.removeAt(Random.nextInt(0, data.size))
            data2 += c
        }

        val adapter = CardsAdapter(data2)
        val recyclerView = findViewById<RecyclerView>(R.id.cards)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

}