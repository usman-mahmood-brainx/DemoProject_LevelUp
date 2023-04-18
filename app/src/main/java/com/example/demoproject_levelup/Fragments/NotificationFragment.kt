package com.example.demoproject_levelup.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject_levelup.*
import com.example.demoproject_levelup.Adapters.CustomAdapter
import com.example.demoproject_levelup.Models.Notification
import com.example.demoproject_levelup.databinding.FragmentNotificationBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class NotificationFragment(private val context: Context) : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var notificationList: MutableList<Notification>
    private lateinit var adapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false)

        notificationList = mutableListOf<Notification>()

        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString("access-token", null)
        val uid = sharedPreferences.getString("uid", null)
        val client = sharedPreferences.getString("client", null)

        getNotifications(accessToken,uid,client)


        binding.rvNotification.layoutManager = LinearLayoutManager(context)
        adapter = CustomAdapter(notificationList)
        binding.rvNotification.adapter = adapter


        return binding.root
    }


    fun getNotifications(accessToken: String?, uid: String?, client: String?) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(UserService::class.java)
        lifecycleScope.launch {
            try {
                val response: Response<Data> =
                    retrofitInstance.getAnnouncements(accessToken, uid, client)
                if (response.isSuccessful()) {
                    val statusCode = response.code()
                    if (statusCode == 200) {
                        val list = response.body()?.announcements
                        list?.forEach {
                            notificationList.add(
                                Notification(
                                    it.sent_by_name,
                                    it.sent_by_role,
                                    it.title,
                                    it.description,
                                    calculatingTimePassed(it.created_at)
                                )
                            )
                            adapter.notifyItemInserted(notificationList.size - 1)
                        }
                    }
                } else {
                    // If Error
                    val errorJsonString = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorJsonString)
                    val errorMessage = jsonObject.getString("error")
                    Toast.makeText(context, "Error : $errorMessage", Toast.LENGTH_SHORT).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }

    }
    fun calculatingTimePassed(date:String):String{
        val createdAtString = date
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        val createdAt = format.parse(createdAtString)

        val currentTimeInMillis = System.currentTimeMillis()
        val createdAtInMillis = createdAt.time
        val timePassedInMillis = currentTimeInMillis - createdAtInMillis

        val secondsPassed = timePassedInMillis / 1000
        val minutesPassed = secondsPassed / 60
        val hoursPassed = minutesPassed / 60
        val daysPassed = hoursPassed / 24

        val timePassed = when {
            daysPassed > 0 -> "$daysPassed days ago"
            hoursPassed > 0 -> "$hoursPassed hours ago"
            minutesPassed > 0 -> "$minutesPassed minutes ago"
            else -> "$secondsPassed seconds ago"
        }
        return timePassed
    }

}