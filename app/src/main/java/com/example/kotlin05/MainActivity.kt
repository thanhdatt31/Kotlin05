package com.example.kotlin05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kotlin05.adapter.DayFragmentAdapter
import com.example.kotlin05.fragment.DayFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    private lateinit var selectedDate: LocalDate
    private lateinit var adapter: DayFragmentAdapter
    private lateinit var viewPager2: ViewPager
    private var dayFragment: DayFragment = DayFragment()
    private var isLeft: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = DayFragmentAdapter(supportFragmentManager)
        viewPager2 = viewPager
        viewPager2.adapter = adapter
        selectedDate = LocalDate.now()
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
                if (position < adapter.count) {
                    adapter.getFragment(position + 1)?.clearSelect()
                }

//                dayFragment.updateDay(2, dayFragment.genListDay(2, position.toLong()))
                val testList = dayFragment.genListDay(1, position.toLong())
                dayFragment.updateDay(1, testList)
                adapter.notifyDataSetChanged()
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

    }

}




