package com.example.kotlin05

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type
import java.time.LocalDate
import java.util.*


class CalendarAdapter() : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    private var listDays: ArrayList<String> = arrayListOf()
    var dayOfWeek: Int = 0
    var abc2: Int = 0
    var rowIndex = -1
    var clickCount = 0
    var startTime: Long = 0
    var duration: Long = 0
    val MAX_DURATION = 500

    @SuppressLint("ClickableViewAccessibility")
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)
        var itemBg: ConstraintLayout = itemView.findViewById(R.id.item_bg)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_date, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: CalendarAdapter.ViewHolder, position: Int) {
        holder.dayOfMonth.text = listDays[position]
        holder.itemBg.setOnClickListener {
            rowIndex = position
            notifyDataSetChanged()
        }
        if (rowIndex == position) {
            holder.dayOfMonth.setTextColor(Color.parseColor("#e600e6"))
            holder.dayOfMonth.typeface = Typeface.DEFAULT_BOLD
            holder.itemBg.setOnTouchListener { v, event ->
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_DOWN -> {
                        startTime = System.currentTimeMillis()
                        clickCount++
                    }
                    MotionEvent.ACTION_UP -> {
                        val time = System.currentTimeMillis() - startTime
                        duration += time
                        if (clickCount == 2) {
                            if (duration <= MAX_DURATION) {
                                val rnd = Random()
                                val color = Color.argb(
                                    255,
                                    rnd.nextInt(256),
                                    rnd.nextInt(256),
                                    rnd.nextInt(256)
                                )
                                holder.dayOfMonth.setTextColor(color)
                            }
                            clickCount = 0
                            duration = 0
                        }
                    }
                }
                true
            }
        } else {
            holder.dayOfMonth.setTextColor(Color.parseColor("#FFFFFF"))
            if (position < dayOfWeek - 1 || position > listDays.size - 1 - abc2) {
                holder.dayOfMonth.setTextColor(Color.parseColor("#FF888888"))
            }
        }

    }

    override fun getItemCount(): Int {
        return listDays.size
    }

    fun setData(data: ArrayList<String>) {
        listDays = data
        notifyDataSetChanged()
    }

    fun getDayOfWeek(data: Int, abc: Int) {
        dayOfWeek = data
        abc2 = abc
        notifyDataSetChanged()
    }
}