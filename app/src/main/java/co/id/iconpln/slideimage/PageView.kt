package co.id.iconpln.slideimage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter

class PageView: PagerAdapter {
    lateinit var context: Context
    lateinit var path: IntArray
    lateinit var inflater: LayoutInflater

    constructor(context: Context, path: IntArray) : super() {
        this.context = context
        this.path = path
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as RelativeLayout
    }

    override fun getCount(): Int {
        return path.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var img: ImageView
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rv: View = inflater.inflate(R.layout.swipe_fragment, container, false)
        img = rv.findViewById(R.id.img) as ImageView
        img.setImageResource(path[position])
        container!!.addView(rv)
        return rv
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as RelativeLayout)
    }

}