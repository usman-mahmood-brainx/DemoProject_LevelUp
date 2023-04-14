package com.example.demoproject_levelup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView

class SettingFragment(context:Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        val settingList = listOf<SettingItem>(
            SettingItem(R.drawable.profile_icon, "Profile"),
            SettingItem(R.drawable.change_pass_icon, "Change Password"),
            SettingItem(R.drawable.flag_icon, "Coaching Plans"),
            SettingItem(R.drawable.mail_icon, "Contact Us"),
            SettingItem(R.drawable.lock_icon, "Privacy Policy"),
            SettingItem(R.drawable.faq_icon, "FAQ'S")
        )

        val gridLayout = view.findViewById<GridLayout>(R.id.gridLayoutSetting)

        settingList.forEach{
            val view = inflateLayout(gridLayout,it)
            gridLayout.addView(view)
        }

        return view
    }

    fun inflateLayout(gridLayout: GridLayout, settingItem: SettingItem): View {
        // Inflating Card View Layout
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.setting_card_item, gridLayout, false)
        // Setting textView and Image
        val ivIcon = view.findViewById<ImageView>(R.id.ivSettingIcon)
        ivIcon.setImageResource(settingItem.icon)
        val tvSettingTitle = view.findViewById<TextView>(R.id.tvSettingTitle)
        tvSettingTitle.text = settingItem.title

        return view
    }

}