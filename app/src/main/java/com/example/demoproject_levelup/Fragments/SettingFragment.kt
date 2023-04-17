package com.example.demoproject_levelup.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.example.demoproject_levelup.Models.SettingItem
import com.example.demoproject_levelup.R
import com.example.demoproject_levelup.databinding.FragmentSettingBinding
import com.example.demoproject_levelup.databinding.SettingCardItemBinding

class SettingFragment(context:Context) : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false)


        val settingList = listOf<SettingItem>(
            SettingItem(R.drawable.profile_icon, "Profile"),
            SettingItem(R.drawable.change_pass_icon, "Change Password"),
            SettingItem(R.drawable.flag_icon, "Coaching Plans"),
            SettingItem(R.drawable.mail_icon, "Contact Us"),
            SettingItem(R.drawable.lock_icon, "Privacy Policy"),
            SettingItem(R.drawable.faq_icon, "FAQ'S")
        )


        settingList.forEach{
            val view = inflateLayout(binding.gridLayoutSetting,it)
            binding.gridLayoutSetting.addView(view)
        }

        return binding.root
    }

    fun inflateLayout(gridLayout: GridLayout, settingItem: SettingItem): View {
        // Inflating Card View Layout
        val inflater = LayoutInflater.from(context)

        val settingItemBinding =  SettingCardItemBinding.inflate(inflater, gridLayout, false)

        // Setting textView and Image

        settingItemBinding.ivSettingIcon.setImageResource(settingItem.icon)

        settingItemBinding.tvSettingTitle.text = settingItem.title

        return settingItemBinding.root
    }

}