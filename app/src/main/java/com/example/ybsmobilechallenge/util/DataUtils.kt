package com.example.ybsmobilechallenge.util

import com.example.ybsmobilechallenge.model.response.Tags

fun getConcatenatedTagsContent(tags: Tags, delimiter: String = ", "): String {
    return tags.tag.joinToString(separator = delimiter) { it.content }
}

// https://www.flickr.com/services/api/misc.urls.html
fun constructUrl(server: String, id: String, secret: String): String {
    return "https://live.staticflickr.com/${server}/${id}_${secret}.jpg"
}

// https://www.flickr.com/services/api/misc.buddyicons.html
fun constructUserIconUrl(iconFarm: String, server: String, nsid: String): String {
    return "https://farm${iconFarm}.staticflickr.com/${server}/buddyicons/${nsid}.jpg"
}