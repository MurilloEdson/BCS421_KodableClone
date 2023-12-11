package edu.farmingdale.kodable.levels

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import edu.farmingdale.kodable.GridAdapter
import edu.farmingdale.kodable.R

class Level6 : AppCompatActivity() {
    lateinit var gridView: GridView
    var imagList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level6)
        val charBack = findViewById<ImageView>(R.id.charac)
        gridView = findViewById<GridView>(R.id.path)
        val leftImg = findViewById<ImageButton>(R.id.left)
        val rightImg = findViewById<ImageButton>(R.id.right)
        val upImg = findViewById<ImageButton>(R.id.up)
        val downImg = findViewById<ImageButton>(R.id.down)
        val input = findViewById<ImageView>(R.id.frame)

        charBack.setBackgroundResource(R.color.white)
        imagList = ArrayList<Int>()
        imagList.add(R.drawable.viking_front2)
        for(i in 0..98){
            imagList.add(R.drawable.grass)
        }
        val pathAdapter = GridAdapter(imagList,this)
        gridView.adapter  = pathAdapter
        leftImg.setOnLongClickListener(onLongClickListener)
        rightImg.setOnLongClickListener(onLongClickListener)
        upImg.setOnLongClickListener(onLongClickListener)
        downImg.setOnLongClickListener(onLongClickListener)
        input.setOnDragListener(arrowDragListener)
    }
    private val arrowDragListener = View.OnDragListener { view, dragEvent ->
        (view as? ImageView)?.let {
            when (dragEvent.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_EXITED-> {
                    return@OnDragListener true
                }
                // No need to handle this for our use case.
                DragEvent.ACTION_DRAG_LOCATION -> {
                    return@OnDragListener true
                }

                DragEvent.ACTION_DROP -> {
                    // Read color data from the clip data and apply it to the card view background.
                    val item: ClipData.Item = dragEvent.clipData.getItemAt(0)
                    val lbl = item.text.toString()
                    Log.d("BCCCCCCCCCCC", "NOTHING > >  " + lbl)
                    when(lbl.toString()){
                        "UP"->view.setImageResource( R.drawable.arrow_up2)
                        "DOWN"->view.setImageResource( R.drawable.arrow_down2)
                        "FORWARD"->view.setImageResource( R.drawable.arrow_right2)
                        "BACK"->view.setImageResource( R.drawable.arrow_left2)
                    }
                    return@OnDragListener true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    return@OnDragListener true
                }
                else -> return@OnDragListener false
            }
        }
        false
    }
    private val onLongClickListener = View.OnLongClickListener { view: View ->
        (view as? ImageButton)?.let {

            val item = ClipData.Item(it.tag as? CharSequence)

            val dragData = ClipData( it.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item)
            val myShadow = ArrowDragShadowBuilder(it)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(dragData, myShadow, null, 0)
            } else {
                it.startDrag(dragData, myShadow, null, 0)
            }
            true
        }
        false
    }
    private class ArrowDragShadowBuilder(view: View) : View.DragShadowBuilder(view) {
        private val shadow = view.background
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            val width: Int = view.width
            val height: Int = view.height
            shadow?.setBounds(0, 0, width, height)
            size.set(width, height)
            touch.set(width / 2, height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            shadow?.draw(canvas)
        }
    }
}