package com.example.demoproject_levelup.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject_levelup.Models.Notification
import com.example.demoproject_levelup.R

class CustomAdapter(private val notificationList: List<Notification>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_card_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val notification = notificationList[position]

        holder.tvSender.text = notification.senderName
        holder.tvDesignation.text = notification.designation
        holder.tvSubject.text = notification.subject
        holder.tvMessage.text = notification.notificationMessage
        holder.tvTimePassed.text = notification.timePassed

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return notificationList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(NotificationView: View) : RecyclerView.ViewHolder(NotificationView) {
        val tvSender: TextView = NotificationView.findViewById(R.id.tvSenderName)
        val tvDesignation: TextView = itemView.findViewById(R.id.tvDesignation)
        val tvSubject: TextView = NotificationView.findViewById(R.id.tvSubject)
        val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        val tvTimePassed: TextView = itemView.findViewById(R.id.tvTimePassed)
    }
}