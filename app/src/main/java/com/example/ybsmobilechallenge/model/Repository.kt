package com.example.ybsmobilechallenge.model

import com.example.ybsmobilechallenge.model.response.Photo
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
        tags: String?,
        matchAllTags: Boolean,
        userId: String?
    ): List<PhotoDetails> = withContext(Dispatchers.IO) {
        val getResult = apiHelper.fetchPhotos(tags = tags, tagMode = matchAllTags, userId = userId)
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
     * Retrieves a list of images based on a given userId.
     *
     * The function does two things:
     * 1. Fetches a list of photos based on the search query.
     * 2. If the result of the above is a success and not null, it then concurrently fetches
     *    the info from the getInfo endpoint for each image.
     *
     * @param userId The user nsid from the Image Details.
     * @return List<Photo> or an emptyList if no results.
     */
    suspend fun getImagesByUser(userId: String): List<Photo> = withContext(Dispatchers.IO) {
        val result = apiHelper.fetchUserPhotos(userId)
        if (result.isSuccess) {
            result.getOrDefault(emptyList())
        } else {
            emptyList()
        }
    }
}
