package com.example.ybsmobilechallenge.model.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("photos") val photos: Photos?,
    @SerializedName("stat") val stat: String,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null
)


// List of Images
data class Photos(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("perpage") val perpage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("photo") val photoList: List<Photo>
)

// Single Image
data class Photo(
    @SerializedName("id") val id: String,
    @SerializedName("owner") val owner: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: String,
    @SerializedName("farm") val farm: Int,
    @SerializedName("title") val title: String,
    @SerializedName("ispublic") val isPublic: Int,
    @SerializedName("isfriend") val isFriend: Int,
    @SerializedName("isfamily") val isFamily: Int
)

// https://www.flickr.com/services/api/misc.urls.html
fun constructUrl(server: String, id: String, secret: String): String {
    return "https://live.staticflickr.com/${server}/${id}_${secret}.jpg"
}
