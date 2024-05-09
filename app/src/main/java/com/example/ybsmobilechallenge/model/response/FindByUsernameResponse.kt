package com.example.ybsmobilechallenge.model.response

import com.google.gson.annotations.SerializedName

data class FindByUsernameResponse(
    @SerializedName("user") val user: User?,
    @SerializedName("stat") val stat: String,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null
)


// User Info
data class User(
    @SerializedName("id") val id: String,
    @SerializedName("nsid") val nsid: String,
    @SerializedName("username") val username: Username
)

// Username
data class Username(
    @SerializedName("_content") val content: String
)