package com.example.kotlin05.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import com.example.kotlin05.fragment.DayFragment
import java.time.LocalDate


class DayFragmentAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 1000
    }

    override fun getItem(position: Int): Fragment {
        val dayFragment = DayFragment()
        val bundle = Bundle()
        bundle.putString("message", "$position")
        dayFragment.arguments = bundle
        return dayFragment
    }

}