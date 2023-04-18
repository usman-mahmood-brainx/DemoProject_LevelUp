package com.example.demoproject_levelup

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @Headers("Content-Type:application/json")
    @POST("api/v1/users/sign_in.json")
    suspend fun userLogin(@Body request:LoginRequest): Response<LoginResponse>
//    suspend fun userLogin(@Body request:LoginRequest): retrofit2.Call<ResponseBody>


    @DELETE("api/v1/users/sign_out.json")
    @Headers("Content-Type:application/json")
    suspend fun userLogout(
        @Header("access-token") accessToken: String?,
        @Header("uid") uid: String?,
        @Header("client") client: String?): Response<ResponseBody>

    @GET("api/v1/announcements")
    @Headers("Content-Type:application/json")
    suspend fun getAnnouncements(
        @Header("access-token") accessToken: String?,
        @Header("uid") uid: String?,
        @Header("client") client: String?): Response<Data>
}