package co.id.iconpln.slideimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var dotsLayout: LinearLayout
    lateinit var mPager: ViewPager
    lateinit var toolbar: Toolbar
    var path: IntArray = intArrayOf(R.drawable.slide1,R.drawable.slide2,R.drawable.slide3)
    lateinit var dots: Array<ImageView>
    lateinit var adapter: PageView

    var currentPage: Int = 0
    lateinit var timer: Timer
    val DELAY_MS: Long = 3000
    val PERIOD_MS: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        setupImageSlider()
        setupDots()
    }

    private fun setupActionBar(){
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    private fun setupImageSlider(){
        mPager = findViewById(R.id.pager) as ViewPager
        adapter = PageView(this, path)
        mPager.adapter = adapter
    }

    private fun setupDots(){
        dotsLayout = findViewById(R.id.dotsLayout) as LinearLayout
        createDots(0)
        updatePage()
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                createDots(position)
            }
        })
    }

    private fun updatePage(){
        var handler = Handler()
        val Update: Runnable = Runnable {
            if(currentPage == path.size){
                currentPage = 0
            }
            mPager.setCurrentItem(currentPage++, true)
        }
        timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
    }

    private fun createDots(position: Int){
        if (dotsLayout!=null){
            dotsLayout.removeAllViews()
        }
        dots = Array(path.size, {i -> ImageView(this)})

        for(i in 0..path.size - 1){
            dots[i] = ImageView(this)
            if (i == position){
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots))
            } else {
                dots[i].setImageDrawable((ContextCompat.getDrawable(this, R.drawable.inactive_dots)))
            }
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(4,0,4,0)
            dotsLayout.addView(dots[i],params)
        }
    }
}
