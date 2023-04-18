package com.example.demoproject_levelup.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.demoproject_levelup.LoginActivity
import com.example.demoproject_levelup.Models.SettingItem
import com.example.demoproject_levelup.R
import com.example.demoproject_levelup.RetrofitInstance
import com.example.demoproject_levelup.UserService
import com.example.demoproject_levelup.databinding.FragmentSettingBinding
import com.example.demoproject_levelup.databinding.SettingCardItemBinding
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class SettingFragment(private val context:Context) : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences.getString("access-token",null)
        val uid = sharedPreferences.getString("uid",null)
        val client = sharedPreferences.getString("client",null)

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

        binding.btnLogout.setOnClickListener {
            logout(accessToken,client,uid)
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

    fun logout(accessToken: String?, client: String?, uid: String?) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(UserService::class.java)


        lifecycleScope.launch {
            try {
                Toast.makeText(context, "$accessToken + $client + $uid", Toast.LENGTH_LONG).show()
                val response: Response<ResponseBody> = retrofitInstance.userLogout(accessToken,uid,client)
                if (response.isSuccessful()) {
                    val statusCode = response.code()
                    if (statusCode == 200) {

                        updateHeaders()

                        val intent = Intent(context, LoginActivity::class.java)
                        requireActivity().finish()
                        Toast.makeText(context, "Logout Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    }
                } else {
                    // If Error
                    val statusCode = response.code()
                    val errorJsonString = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorJsonString)
                    val errorMessage = jsonObject.getString("error")
                    Toast.makeText(context, "Error : $errorMessage + Status Code: $statusCode", Toast.LENGTH_SHORT)
                        .show()
                }

            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }

    private fun updateHeaders() {
        val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("IsLogin", false)

        editor.apply()
    }

}