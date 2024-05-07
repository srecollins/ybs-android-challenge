package com.example.ybsmobilechallenge.network

import com.example.ybsmobilechallenge.model.response.Photo
import com.example.ybsmobilechallenge.model.response.PhotoDetails

class ApiHelper(private val apiService: ApiService) {
    suspend fun fetchPhotos(text: String?): Result<List<Photo>> {
        val response = apiService.getPhotos(text = text ?: "Yorkshire", userId = null)
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

    suspend fun fetchUserPhotos(userId: String): Result<List<Photo>> {
        val response = apiService.getPhotos(text = "", userId = userId)
        if (response.isSuccessful && response.body()?.stat == "ok") {
            return Result.success(response.body()?.photos?.photoList ?: listOf())
        } else {
            val errorBody = response.body()
            return Result.failure(Exception("Error ${errorBody?.code}: ${errorBody?.message}"))
        }
    }
}
