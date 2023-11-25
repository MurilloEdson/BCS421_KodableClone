package edu.farmingdale.kodable

import android.media.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import androidx.core.graphics.drawable.toDrawable

class Gameplay : AppCompatActivity() {

    lateinit var gridView: GridView
    var imagList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        imagList = ArrayList<Int>()
        gridView = findViewById<GridView>(R.id.path)
        gridView.setBackgroundResource(resources.getColor(android.R.color.transparent))
        imagList.add(R.drawable.viking_front2)
        for(i in 0..98){
            imagList.add(R.drawable.grass)
        }
        val pathAdapter = GridAdapter(imagList,this)
        gridView.adapter  = pathAdapter
    }
}