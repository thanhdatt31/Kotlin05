package com.example.kotlin05.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kotlin05.fragment.DayFragment


class DayFragmentAdapter(fm: FragmentManager) :
//nay a viet o day
// bao sao
    FragmentPagerAdapter(fm) {
    // nay a viet thieu cai j ma no bao loi nhi
     var mPageReferenceMap = HashMap<Int, DayFragment>()
    override fun getCount(): Int {
        return 1000
    }

    override fun getItem(position: Int): Fragment {
        val dayFragment = DayFragment()
        val bundle = Bundle()
        bundle.putString("message", "$position")
        dayFragment.arguments = bundle
        mPageReferenceMap[position] = dayFragment
        return dayFragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        mPageReferenceMap.remove(position)
    }

     fun getFragment(position : Int): DayFragment? {
        return mPageReferenceMap[position]
    }


}