package com.example.ybsmobilechallenge.model

import com.example.ybsmobilechallenge.model.response.Owner
import com.example.ybsmobilechallenge.model.response.Photo
import com.example.ybsmobilechallenge.model.response.Tags
import com.google.gson.annotations.SerializedName

data class ImageDetails(
    @SerializedName("basicInfo") val basicInfo: Photo,
    @SerializedName("tags") val tags: Tags?,
    @SerializedName("owner") val owner: Owner?,
)