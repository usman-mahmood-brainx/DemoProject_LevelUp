package com.example.demoproject_levelup

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.demoproject_levelup.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject

import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val isLogin = sharedPreferences.getBoolean("IsLogin", false)
        if (isLogin) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            finish()
            startActivity(intent)
        } else {

            binding.btnLogin.setOnClickListener {
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()

                Toast.makeText(this@LoginActivity,"$email  + $password",Toast.LENGTH_SHORT).show()
                if (email != "" && password != "") {
                    login(email, password)
                } else {
                    Toast.makeText(this@LoginActivity, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    fun login(email: String, password: String) {
        val retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(UserService::class.java)
        val loginInfo = LoginRequest(email, password)

        lifecycleScope.launch {
            try {
                val response: Response<LoginResponse> = retrofitInstance.userLogin(loginInfo)
                if (response.isSuccessful()) {
                    val statusCode = response.code()
                    if (statusCode == 200) {
                        saveHeaders(response)
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        finish()
                        startActivity(intent)
                    }
                } else {
                    // If Error
                    val statusCode = response.code()
                    val errorJsonString = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorJsonString)
                    val errorMessage = jsonObject.getString("error")
                    Toast.makeText(this@LoginActivity, "Error : $errorMessage", Toast.LENGTH_SHORT)
                        .show()
                }

            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }

    }

    private fun saveHeaders(response: Response<LoginResponse>) {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val accessToken = response.headers()["access-token"]
        val client = response.headers()["client"]
        val uid = response.headers()["uid"]
        val name = response.body()?.name

        editor.putString("access-token", accessToken)
        editor.putString("client", client)
        editor.putString("uid", uid)
        editor.putString("name", name)
        editor.putBoolean("IsLogin", true)

        editor.apply()

    }


}