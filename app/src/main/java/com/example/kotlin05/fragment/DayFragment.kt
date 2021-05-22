package com.example.kotlin05.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin05.CalendarAdapter
import com.example.kotlin05.R
import com.example.kotlin05.adapter.DayTitleAdapter
import kotlinx.android.synthetic.main.fragment_day.view.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates


open class DayFragment : Fragment() {
    private lateinit var selectedDate: LocalDate
    private val calendarAdapter: CalendarAdapter = CalendarAdapter()
    private val dayTitleAdapter: DayTitleAdapter = DayTitleAdapter()
    private lateinit var listTitle: ArrayList<Int>
    private val listDay: ArrayList<String> = arrayListOf()
    private var test: Int = 0
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewTitle: RecyclerView
    private var dayOfWeek : Int = 0
    private var switchDay: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_day, container, false)
        val position = arguments!!.getString("message")!!.toLong()
        listTitle = arrayListOf(0, 1, 2, 3, 4, 5, 6)
        selectedDate = LocalDate.now()
        selectedDate = selectedDate.minusMonths(500)
        selectedDate = selectedDate.plusMonths(position)
        view.tv_monthYear.text = monthYearFromDate(selectedDate)
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        dayOfWeek = firstOfMonth.dayOfWeek.value
        val currentMonth = daysInMonthArray(selectedDate)
        val previousMonth = daysInMonthArray(selectedDate.minusMonths(1))
        for (i in 1 until dayOfWeek) {
            listDay.add(0, previousMonth[previousMonth.size - i])
        }
        listDay.addAll(currentMonth)
        test = 42 - listDay.size
        for (i in 1..42 - listDay.size) {
            listDay.add(i.toString())
        }
        recyclerViewTitle = view.tv_day_title
        recyclerViewTitle.apply {
            layoutManager = GridLayoutManager(context, 7)
            dayTitleAdapter.setData(listTitle)
            adapter = dayTitleAdapter
        }
        recyclerView = view.recyclerView
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 7)
            calendarAdapter.setData(listDay)
            calendarAdapter.getDayOfWeek(dayOfWeek, test)
            adapter = calendarAdapter
        }

        val days = resources.getStringArray(R.array.Days)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item, days
            )
        }
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (days[position]) {
                    "MON" -> {
                        switchTitle(0)
                        calendarAdapter.setData(genListDay(0))
                        calendarAdapter.getDayOfWeek(dayOfWeek, test)
                    }
                    "TUE" -> {
                        switchTitle(1)
                        calendarAdapter.setData(genListDay(1))
                        calendarAdapter.getDayOfWeek(dayOfWeek - 1, test)
                    }
                    "WED" -> {switchTitle(2)
                        calendarAdapter.setData(genListDay(2))
                        calendarAdapter.getDayOfWeek(dayOfWeek - 2, test)
                    }
                    "THU" -> {
                        switchTitle(3)
                        calendarAdapter.setData(genListDay(3))
                        calendarAdapter.getDayOfWeek(dayOfWeek - 3, test)
                    }

                    "FRI" -> {
                        switchTitle(4)
                        calendarAdapter.setData(genListDay(4))
                        calendarAdapter.getDayOfWeek(dayOfWeek - 4, test)
                    }
                    "SAT" -> {
                        switchTitle(5)
                        calendarAdapter.setData(genListDay(5))
                        calendarAdapter.getDayOfWeek(dayOfWeek - 5, test)
                    }
                    "SUN" -> {
                        switchTitle(6)
                        calendarAdapter.setData(genListDay(6))
                        calendarAdapter.getDayOfWeek(dayOfWeek - 6, test)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        return view
    }

    private fun genListDay(data : Int) : ArrayList<String>{
        val listDayNew : ArrayList<String> = arrayListOf()
        val currentMonth = daysInMonthArray(selectedDate)
        val previousMonth = daysInMonthArray(selectedDate.minusMonths(1))
        for (i in 1 until dayOfWeek - data) {
            listDayNew.add(0, previousMonth[previousMonth.size - i])
        }
        listDayNew.addAll(currentMonth)
        test = 42 - listDayNew.size
        for (i in 1..42 - listDayNew.size) {
            listDayNew.add(i.toString())
        }
        return listDayNew
    }

    fun clearSelect() {
        Log.d("datnt", "clearSelect: ")
        calendarAdapter.rowIndex = -1
        calendarAdapter.notifyDataSetChanged()
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

    private fun switchTitle(number: Int) {
        listTitle.clear()
        when (number) {
            0 -> listTitle = arrayListOf(0, 1, 2, 3, 4, 5, 6)
            1 -> listTitle = arrayListOf(1, 2, 3, 4, 5, 6, 0)
            2 -> listTitle = arrayListOf(2, 3, 4, 5, 6, 0, 1)
            3 -> listTitle = arrayListOf(3, 4, 5, 6, 0, 1, 2)
            4 -> listTitle = arrayListOf(4, 5, 6, 0, 1, 2, 3)
            5 -> listTitle = arrayListOf(5, 6, 0, 1, 2, 3, 4)
            6 -> listTitle = arrayListOf(6, 0, 1, 2, 3, 4, 5)
        }
        dayTitleAdapter.setData(listTitle)
    }

    override fun onPause() {
        listDay.clear()
        calendarAdapter.rowIndex = -1
        calendarAdapter.notifyDataSetChanged()
        super.onPause()
    }
}
