package com.example.ybsmobilechallenge.model

import com.example.ybsmobilechallenge.model.response.FindByUsernameResponse
import com.example.ybsmobilechallenge.model.response.PhotoDetails
import com.example.ybsmobilechallenge.network.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class ImageRepository(private val apiHelper: ApiHelper) {

    /**
     * Retrieves a list of images based on a search query from the Flickr Search endpoint.
     *
     * The function does two things:
     * 1. Fetches a list of photos based on the search query.
     * 2. If the result of the above is a success and not null, it then concurrently fetches
     *    the info from the getInfo endpoint for each image.
     *
     * @param tags Optional search tags used to query photos.
     * @return List<PhotoDetails> or an emptyList if no results.
     */
    suspend fun getImages(
        text: String?,
        tags: String?,
        matchAllTags: Boolean?,
        userId: String?
    ): List<PhotoDetails> = withContext(Dispatchers.IO) {
        val getResult =
            apiHelper.fetchPhotos(text = text, tags = tags, tagMode = matchAllTags, userId = userId)
        if (getResult.isSuccess) {
            val photos = getResult.getOrNull() ?: emptyList()

            // Fetch details for each photo concurrently
            coroutineScope {
                photos.map { photo ->
                    async {
                        fetchPhotoDetail(photo.id, photo.secret)
                    }
                }.awaitAll().filterNotNull()
            }
        } else {
            emptyList()
        }
    }

    /**
     * Gets additional image information from the Flickr GetInfo endpoint.
     *
     * @param photoId The photo id
     * @param secret The photo secret
     * @return PhotoDetails The full details for the given image
     */
    suspend fun fetchPhotoDetail(photoId: String, secret: String): PhotoDetails? {
        return apiHelper.fetchPhotoDetails(photoId, secret).getOrNull()
    }

    /**
     * Retrieves a user id based on a given username
     *
     * The username API endpoint does a full text search, so the username must be exact
     *
     * @param username The username entered in a Search
     * @return FindByUsernameResponse
     */
    suspend fun getUserByUsername(username: String): FindByUsernameResponse? =
        withContext(Dispatchers.IO) {
            val result = apiHelper.findByUsername(username)
            if (result.isSuccess) {
                result.getOrDefault(null)
            } else {
                null
            }
        }
}
