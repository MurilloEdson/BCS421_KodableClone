package edu.farmingdale.kodable

import android.app.Activity
import android.content.*
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.provider.MediaStore
import android.view.*
import android.widget.*
import androidx.core.app.ActivityCompat.startActivityForResult

internal class GridAdapter(

    private val imageList: List<Int>,
    private val context: Context
) :
    BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var ImageHolder: ImageView

    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        var view = view

        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (view == null) {
            view = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }

        ImageHolder = view?.findViewById<ImageView>(R.id.idImg)!!


        ImageHolder.setImageResource(imageList.get(position))

        return view
    }
}