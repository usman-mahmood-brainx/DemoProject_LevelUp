package com.example.demoproject_levelup

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment


class HomeFragment(private val context: Context) : Fragment() {
    lateinit var tvWelcome: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val name = "John"
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment

        dynamicName(view,name)
        val dashboardList = listOf<DashboardItem>(
            DashboardItem("Do Today",R.drawable.do_today),
            DashboardItem("Activities & Tips",R.drawable.activities_tips),
            DashboardItem("Track It",R.drawable.track_it),
            DashboardItem("Events",R.drawable.event),
            DashboardItem("Training",R.drawable.training),
            DashboardItem("Say & Share",R.drawable.say_share)
        )


        val gridLayout = view.findViewById<GridLayout>(R.id.gridDashboard)

        dashboardList.forEach{
            val view = inflateLayout(gridLayout,it)
            gridLayout.addView(view)
        }

        return view
    }

    fun dynamicName(view: View, name: String){
        val welcome = "Welcome $name"
        val spannableString = SpannableString(welcome)
        spannableString.setSpan(ForegroundColorSpan(Color.BLACK), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(getColor(context,R.color.blue2)), 8,welcome.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvWelcome = view.findViewById<TextView>(R.id.welcome)
        tvWelcome.text = spannableString
    }


    fun inflateLayout(gridLayout: GridLayout, dashboardItem: DashboardItem): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.dashboard_item,gridLayout, false)
        val textView = view.findViewById<TextView>(R.id.tvDashItem)
        textView.text = dashboardItem.name
        val imageView = view.findViewById<ImageView>(R.id.ivDashItem)
        imageView.setImageResource(dashboardItem.image)
        return view
    }


}