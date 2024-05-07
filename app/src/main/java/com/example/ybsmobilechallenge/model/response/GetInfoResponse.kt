package com.example.ybsmobilechallenge.model.response

import com.google.gson.annotations.SerializedName

data class GetInfoResponse(
    @SerializedName("photo") val photo: PhotoDetails?,
    @SerializedName("stat") val stat: String,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null
)

data class PhotoDetails(
    @SerializedName("id") val id: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: String,
    @SerializedName("farm") val farm: Int,
    @SerializedName("dateuploaded") val dateUploaded: String,
    @SerializedName("isfavorite") val isFavorite: Int,
    @SerializedName("license") val license: String,
    @SerializedName("safety_level") val safetyLevel: String,
    @SerializedName("rotation") val rotation: Int,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("title") val title: Content,
    @SerializedName("description") val description: Content,
    @SerializedName("visibility") val visibility: Visibility,
    @SerializedName("dates") val dates: Dates,
    @SerializedName("views") val views: String,
    @SerializedName("tags") val tags: Tags,
    @SerializedName("urls") val urls: Urls,
    @SerializedName("media") val media: String
)

data class Owner(
    @SerializedName("nsid") val nsid: String,
    @SerializedName("username") val username: String,
    @SerializedName("realname") val realname: String,
    @SerializedName("location") val location: String?,
    @SerializedName("iconserver") val iconServer: String,
    @SerializedName("iconfarm") val iconFarm: Int,
    @SerializedName("path_alias") val pathAlias: String,
    @SerializedName("gift") val gift: Gift
)

data class Gift(
    @SerializedName("gift_eligible") val giftEligible: Boolean,
    @SerializedName("eligible_durations") val eligibleDurations: List<String>,
    @SerializedName("new_flow") val newFlow: Boolean
)

data class Content(
    @SerializedName("_content") val content: String
)

data class Visibility(
    @SerializedName("ispublic") val isPublic: Int,
    @SerializedName("isfriend") val isFriend: Int,
    @SerializedName("isfamily") val isFamily: Int
)

data class Dates(
    @SerializedName("posted") val posted: String,
    @SerializedName("taken") val taken: String,
    @SerializedName("takengranularity") val takenGranularity: Int,
    @SerializedName("takenunknown") val takenUnknown: String,
    @SerializedName("lastupdate") val lastUpdate: String
)

data class Tags(
    @SerializedName("tag") val tag: List<Tag>
)

data class Tag(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("authorname") val authorName: String,
    @SerializedName("raw") val raw: String,
    @SerializedName("_content") val content: String,
    @SerializedName("machine_tag") val machineTag: Any
)

data class Urls(
    @SerializedName("url") val url: List<Url>
)

data class Url(
    @SerializedName("type") val type: String,
    @SerializedName("_content") val content: String
)

// https://www.flickr.com/services/api/misc.buddyicons.html
fun constructUserIconUrl(iconFarm: String, server: String, nsid: String): String {
    return "https://farm${iconFarm}.staticflickr.com/${server}/buddyicons/${nsid}.jpg"
}
