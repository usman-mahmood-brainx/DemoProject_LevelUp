package com.example.demoproject_levelup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationFragment(context: Context) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        val notificationList = getDummyNotificationList()
        recyclerView = view.findViewById<RecyclerView>(R.id.rvNotification)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CustomAdapter(notificationList)
        recyclerView.adapter = adapter

        return view
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