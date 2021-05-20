package com.example.kotlin05.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin05.CalendarAdapter
import com.example.kotlin05.R
import kotlinx.android.synthetic.main.fragment_day.view.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.absoluteValue


class DayFragment : Fragment(),CalendarAdapter.OnClickListener {
    private lateinit var selectedDate: LocalDate
    private val calendarAdapter: CalendarAdapter = CalendarAdapter(this)
    private val listDay: ArrayList<String> = arrayListOf()
    private var test: Int = 0
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false)
        val position = arguments!!.getString("message")!!.toLong()
        selectedDate = LocalDate.now()
        selectedDate = selectedDate.minusMonths(500)
        selectedDate = selectedDate.plusMonths(position)
        view.tv_monthYear.text = monthYearFromDate(selectedDate)
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        val currentMonth = daysInMonthArray(selectedDate)
        val previousMonth = daysInMonthArray(selectedDate.minusMonths(1))
        for (i in 1 until dayOfWeek) {
            listDay.add(0, previousMonth[previousMonth.size - i])
        }
        // tat nhac di
        listDay.addAll(currentMonth)
        test = 42 - listDay.size
        for (i in 1..42 - listDay.size) {
            listDay.add(i.toString())
        }

        recyclerView = view.recyclerView
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 7)
            calendarAdapter.setData(listDay)
            calendarAdapter.getDayOfWeek(dayOfWeek, test)
            adapter = calendarAdapter
        }

        return view
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth: LocalDate = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                //
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }
    private fun <T> List<T>.prepareForTwoWayPaging(): List<T> {
        val first = first()
        val last = last()
        return toMutableList().apply {
            add(0, last)
            add(first)
        }
    }

    override fun onClick(position: Int) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
    }
}