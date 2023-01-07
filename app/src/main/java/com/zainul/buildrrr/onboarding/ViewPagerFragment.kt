package com.zainul.buildrrr.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import com.zainul.buildrrr.R
import com.zainul.buildrrr.onboarding.screens.FirstScreen
import com.zainul.buildrrr.onboarding.screens.SecondScreen
import com.zainul.buildrrr.onboarding.screens.ThirdScreen
import kotlinx.android.synthetic.main.fragment_view_pager.*
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val viewPager : ViewPager2 = view.findViewById(R.id.viewPager)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        val WormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        view.viewPager.adapter = adapter
        WormDotsIndicator.attachTo(viewPager)

        return view
    }

}