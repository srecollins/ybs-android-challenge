package com.example.ybsmobilechallenge.network

import com.example.ybsmobilechallenge.model.response.FindByUsernameResponse
import com.example.ybsmobilechallenge.model.response.Photo
import com.example.ybsmobilechallenge.model.response.PhotoDetails

class ApiHelper(private val apiService: ApiService) {
    suspend fun fetchPhotos(
        text: String?,
        tags: String?,
        tagMode: Boolean?,
        userId: String?
    ): Result<List<Photo>> {
        val inlineTagMode = tagMode ?: false
        val response = apiService.getPhotos(
            text = text,
            tags = tags,
            userId = userId,
            tagMode = TagModeMapping.tagFromChecked(inlineTagMode).tagModeString
        )
        if (response.isSuccessful && response.body()?.stat == "ok") {
            return Result.success(response.body()?.photos?.photoList ?: listOf())
        } else {
            val errorBody = response.body()
            return Result.failure(Exception("Error ${errorBody?.code}: ${errorBody?.message}"))
        }
    }

    suspend fun fetchPhotoDetails(photoId: String, secret: String): Result<PhotoDetails> {
        val response = apiService.getPhotoInfo(photoId = photoId, secret = secret)
        if (response.isSuccessful && response.body()?.stat == "ok" && response.body() != null && response.body()?.photo != null) {
            return Result.success(response.body()!!.photo!!)
        } else {
            val errorBody = response.body()
            return Result.failure(Exception("Error ${errorBody?.code}: ${errorBody?.message}"))
        }
    }

    suspend fun findByUsername(username: String): Result<FindByUsernameResponse?> {
        val response = apiService.findByUsername(username = username)
        if ((response.isSuccessful && (response.body()?.stat == "ok") || response.body()?.message == "User not found")) {
            return Result.success(response.body())
        } else {
            val errorBody = response.body()
            return Result.failure(Exception("Error ${errorBody?.code}: ${errorBody?.message}"))
        }
    }
}
