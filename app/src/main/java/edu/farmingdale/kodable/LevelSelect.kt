package edu.farmingdale.kodable

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.farmingdale.kodable.levels.*


class LevelSelect : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)
        val lvl1 = findViewById<ImageView>(R.id.lvl1)
        val lvl2 = findViewById<ImageView>(R.id.lvl2)
        val lvl3 = findViewById<ImageView>(R.id.lvl3)
        val lvl4 = findViewById<ImageView>(R.id.lvl4)
        val lvl5 = findViewById<ImageView>(R.id.lvl5)
        val lvl6 = findViewById<ImageView>(R.id.lvl6)
        lvl1.setOnClickListener{ goToLvl(lvl1) }
        lvl2.setOnClickListener{ goToLvl(lvl2) }
        lvl3.setOnClickListener{ goToLvl(lvl3) }
        lvl4.setOnClickListener{ goToLvl(lvl4) }
        lvl5.setOnClickListener{ goToLvl(lvl5) }
        lvl6.setOnClickListener{ goToLvl(lvl6) }
    }

    private fun goToLvl(v: View?){
        when(v!!.tag){
            "ONE"->{startActivity(Intent(this,Level1::class.java))}
            "TWO"->{startActivity(Intent(this,Level2::class.java))}
            "THREE"->{startActivity(Intent(this,Level3::class.java))}
            "FOUR"->{startActivity(Intent(this,Level4::class.java))}
            "FIVE"->{startActivity(Intent(this,Level5::class.java))}
            "SIX"->{startActivity(Intent(this,Level6::class.java))}
        }
    }
}