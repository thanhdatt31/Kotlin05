package com.example.kotlin05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kotlin05.adapter.DayFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    private lateinit var selectedDate: LocalDate
    private lateinit var adapter: DayFragmentAdapter
    private lateinit var viewPager2: ViewPager
    private var isLeft: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = DayFragmentAdapter(supportFragmentManager)
        viewPager2 = viewPager
        viewPager2.adapter = adapter
        selectedDate = LocalDate.now()
        val month = selectedDate.month.value
        viewPager2.currentItem = 500

        viewPager2.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (position > 0) {
                    adapter.getFragment(position - 1)?.clearSelect()
                }
                if (position < adapter.count) {//build lau vl
                    // e moi wipe may ao
                    adapter.getFragment(position + 1)?.clearSelect()
                }

            }

            // dinh? lun  a oi
            // code tiep di a xem annotation// nhung e chua nghi ra lam sao de colorSecondaryVariant
            // thoi m bo them 2tr a day cho khoa nang cao :))
            override fun onPageScrollStateChanged(state: Int) {
            }

        })

    }

}




