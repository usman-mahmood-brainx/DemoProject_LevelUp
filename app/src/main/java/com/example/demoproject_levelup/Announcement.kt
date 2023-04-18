package com.example.demoproject_levelup

data class Announcement(
    val announcement_type: String,
    val created_at: String,
    val description: String,
    val id: Int,
    val sent_by_name: String,
    val sent_by_role: String,
    val title: String
)