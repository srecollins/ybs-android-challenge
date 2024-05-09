package com.example.ybsmobilechallenge.network

import com.example.ybsmobilechallenge.model.response.FindByUsernameResponse
import com.example.ybsmobilechallenge.model.response.GetInfoResponse
import com.example.ybsmobilechallenge.model.response.SearchResponse
import com.example.ybsmobilechallenge.util.Constants
import com.example.ybsmobilechallenge.util.Endpoints
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

enum class TagModeMapping(val tagModeString: String, val isChecked: Boolean) {
    ANY("all", true),
    ALL("any", false);

    companion object {
        fun tagFromChecked(value: Boolean): TagModeMapping =
            entries.first { it.isChecked == value }
    }
}

interface ApiService {
    @GET("rest")
    suspend fun getPhotos(
        @Query("method") method: String = Endpoints.FLICKR_GET_PHOTOS_METHOD,
        @Query("api_key") apiKey: String = Constants.FLICKR_API_KEY,
        @Query("user_id") userId: String?,
        @Query("format") format: String = "json",
        @Query("tags") tags: String?,
        @Query("text") text: String?,
        @Query("tag_mode") tagMode: String = "any",
        @Query("per_page") perPage: Int = 10,
        @Query("safe_search") safeSearch: Int = 1,
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Response<SearchResponse>

    @GET("rest")
    suspend fun getPhotoInfo(
        @Query("method") method: String = Endpoints.FLICKR_GET_PHOTO_INFO_METHOD,
        @Query("api_key") apiKey: String = Constants.FLICKR_API_KEY,
        @Query("photo_id") photoId: String,
        @Query("format") format: String = "json",
        @Query("secret") secret: String,
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Response<GetInfoResponse>

    @GET("rest")
    suspend fun findByUsername(
        @Query("method") method: String = Endpoints.FLICKR_GET_USER_ID_METHOD,
        @Query("api_key") apiKey: String = Constants.FLICKR_API_KEY,
        @Query("username") username: String,
        @Query("format") format: String = "json",
        @Query("safe_search") safeSearch: Int = 1,
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Response<FindByUsernameResponse>

}
