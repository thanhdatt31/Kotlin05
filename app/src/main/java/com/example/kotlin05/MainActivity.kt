package com.example.kotlin05

import android.os.Bundle
import android.util.Log
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

    }

}




