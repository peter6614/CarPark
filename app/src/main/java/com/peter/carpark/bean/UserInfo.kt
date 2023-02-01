package com.peter.carpark.bean

data class UserInfo(
    val createdAt: String,
    val name: String,
    val objectId: String,
    val phone: String,
    val sessionToken: String,
    val timezone: String,
    val updatedAt: String
)