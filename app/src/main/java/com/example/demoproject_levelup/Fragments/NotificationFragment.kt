package com.example.demoproject_levelup.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject_levelup.Adapters.CustomAdapter
import com.example.demoproject_levelup.Models.Notification
import com.example.demoproject_levelup.databinding.FragmentNotificationBinding

class NotificationFragment(context: Context) : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false)

        val notificationList = getDummyNotificationList()

        binding.rvNotification.layoutManager = LinearLayoutManager(context)
        val adapter = CustomAdapter(notificationList)
        binding.rvNotification.adapter = adapter

        return binding.root
    }

    fun getDummyNotificationList(): MutableList<Notification>{
       return mutableListOf<Notification>(
            Notification(
                "Bryan Sheppard",
                "Manager",
                "Attn: Sales Meeting",
                "Good morning Coldwell Banker Agents!",
                "12m ago"
            ),
            Notification(
                "Bryan Sheppard",
                "Manager",
                "Congratulations!",
                "You've achieved 25% of your Gross Commission",
                "12m ago"
            ),
            Notification(
                "Bryan Sheppard",
                "Manager",
                "Congratulations!",
                "Good morning Coldwell Banker Agents!",
                "12m ago"
            ),
            Notification(
                "Bryan Sheppard",
                "Manager",
                "Attn: Sales Meeting",
                "Good morning Coldwell Banker Agents! The meeting has be…",
                "12m ago"
            ),
        )
    }


}