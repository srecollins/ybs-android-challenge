package com.example.ybsmobilechallenge.util

object Constants {
    const val FLICKR_BASE_ENDPOINT = "https://api.flickr.com/services/"
    const val FLICKR_API_KEY = "b86d1971bbd095236fb14282fca32506" // Usually wouldn't put this somewhere it is committed - but here so it can be tested.
}

object Endpoints {
    const val FLICKR_GET_PHOTOS_METHOD = "flickr.photos.search"
    const val FLICKR_GET_PHOTO_INFO_METHOD = "flickr.photos.getInfo"
    const val FLICKR_GET_USER_ID_METHOD = "flickr.people.findByUsername"
}
