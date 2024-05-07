package com.example.ybsmobilechallenge.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("stat") val stat: String,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)