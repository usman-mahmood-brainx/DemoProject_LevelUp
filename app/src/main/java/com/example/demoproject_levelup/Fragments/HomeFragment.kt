package com.example.demoproject_levelup.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import com.example.demoproject_levelup.Models.DashboardItem
import com.example.demoproject_levelup.R
import com.example.demoproject_levelup.databinding.DashboardItemBinding
import com.example.demoproject_levelup.databinding.FragmentHomeBinding


class HomeFragment(private val context: Context) : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name",null)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.tvName.text = name
        // Inflate the layout for this fragment

        val dashboardList = listOf<DashboardItem>(
            DashboardItem("Do Today", R.drawable.do_today),
            DashboardItem("Activities & Tips", R.drawable.activities_tips),
            DashboardItem("Track It", R.drawable.track_it),
            DashboardItem("Events", R.drawable.event),
            DashboardItem("Training", R.drawable.training),
            DashboardItem("Say & Share", R.drawable.say_share)
        )




        dashboardList.forEach{
            val view = inflateLayout(binding.gridDashboard,it)
            binding.gridDashboard.addView(view)
        }

        return binding.root
    }




    fun inflateLayout(gridLayout: GridLayout, dashboardItem: DashboardItem): View {
        // Inflating Card View Layout
        val inflater = LayoutInflater.from(context)

        val dashboardbinding = DashboardItemBinding.inflate(inflater, gridLayout, false)

        // Setting textView and Image

        dashboardbinding.tvDashItem.text = dashboardItem.name
        dashboardbinding.ivDashItem.setImageResource(dashboardItem.image)

        return dashboardbinding.root
    }


}